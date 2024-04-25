import { Component, Vue, Inject } from 'vue-property-decorator';

import { IAdvertisement } from '@/shared/model/advertisement.model';
import { IPortalUser } from '@/shared/model/portal-user.model';
import { ICompany } from '@/shared/model/company.model';

import InquiryService from './inquiry.service';
import AccountService from '@/account/account.service';
import AdvertisementService from './advertisement.service';
import PortalUserService from '../../entities/portal-user/portal-user.service';

interface InquiryDTO {
  advertisement: IAdvertisement;
  datetime: Date;
  subject: '';
  content: '';
  companySender: ICompany;
  companyReceiver: ICompany;
  portalUserSender: IPortalUser;
}

@Component
export default class AdvertisementDetails extends Vue {
  @Inject('advertisementService') private advertisementService: () => AdvertisementService;
  @Inject('accountService') private accountService: () => AccountService;
  @Inject('portalUserService') private portalUserService: () => PortalUserService;
  @Inject('inquiryService') private inquiryService: () => InquiryService;

  private portalUser: IPortalUser = null;
  private hasAnyAuthorityValue = false;
  private isCompanyOwnerValue = false;

  public advertisement: IAdvertisement = {};
  public inquiryDTO: InquiryDTO | null = null;

  public isModalFormIsValid: boolean = true;
  public inputSubject = {
    value: '',
    isValid: true,
  };
  public textareaContent = {
    value: '',
    isValid: true,
  };

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.advertisementId) {
        vm.retrieveAdvertisement(to.params.advertisementId);
      }
    });
  }

  public retrieveAdvertisement(advertisementId) {
    this.advertisementService()
      .find(advertisementId)
      .then(res => {
        this.advertisement = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public get authenticated(): boolean {
    return this.$store.getters.authenticated;
  }

  public hasAnyAuthority(authorities: any): boolean {
    this.accountService()
      .hasAnyAuthorityAndCheckAuth(authorities)
      .then(value => {
        this.hasAnyAuthorityValue = value;
      });
    return this.hasAnyAuthorityValue;
  }

  public getExpirationDate(advertisement: IAdvertisement): Date {
    const expirationDate = new Date(advertisement.activationDatetime);
    expirationDate.setMonth(expirationDate.getMonth() + Number(advertisement.duration.duration));

    return expirationDate;
  }

  public isCompanyOwner(): boolean {
    const user = this.$store.getters.account;
    if (user) {
      this.portalUserService()
        .findByUserId(user.id)
        .then(res => {
          this.portalUser = res;
          if (this.portalUser.company?.id === this.advertisement.company.id) {
            this.isCompanyOwnerValue = true;
          }
        });
    }
    return this.isCompanyOwnerValue;
  }

  public prepareAdInquiry(instance: IAdvertisement): void {
    this.inquiryDTO = {
      advertisement: this.advertisement,
      datetime: new Date(),
      subject: '',
      content: '',
      companySender: this.portalUser.company,
      companyReceiver: this.advertisement.company,
      portalUserSender: this.portalUser,
    };

    if (<any>this.$refs.adInquiry) {
      (<any>this.$refs.adInquiry).show();
    }
  }

  public closeAdInquiry(): void {
    (<any>this.$refs.adInquiry).hide();
  }

  public validateModalForm() {
    this.isModalFormIsValid = true;
    if (this.inputSubject.value === '') {
      this.inputSubject.isValid = false;
      this.isModalFormIsValid = false;
    }

    if (this.textareaContent.value === '') {
      this.textareaContent.isValid = false;
      this.isModalFormIsValid = false;
    }
  }

  public clearValidity(input) {
    this[input].isValid = true;
  }

  public sendInquiry(): void {
    console.log(this.inquiryDTO);

    this.validateModalForm();

    if (!this.isModalFormIsValid) {
      return;
    }

    this.inquiryDTO.subject = this.inputSubject.value;
    this.inquiryDTO.content = this.textareaContent.value;

    if (this.inquiryDTO) {
      this.inquiryService()
        .create(this.inquiryDTO)
        .then(res => {
          const message = 'Vaš upit za oglas "' + this.advertisement.title + '" je poslat.';
          this.$notify({
            text: message,
          });
        });
    }

    this.closeAdInquiry();
  }
}
