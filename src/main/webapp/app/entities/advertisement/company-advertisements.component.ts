import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IAdvertisement } from '@/shared/model/advertisement.model';
import { ICompany } from '@/shared/model/company.model';

import AlertMixin from '@/shared/alert/alert.mixin';

import AdvertisementService from './advertisement.service';

import AdvertisementTypeService from '../advertisement-status/advertisement-type.service';

import AccountService from '@/account/account.service';

import CompanyService from '@/entities/company.service';

enum AdvertisementType {
  OFFER = 'Понуда',
  DEMAND = 'Потражња',
}

enum AdvertisementStatus {
  ACTIVE = 'Активан',
  INACTIVE = 'Неактиван',
  ARCHIVED = 'Архивиран',
}

enum AdvertisementFilter {
  ALL = 'all',
  OFFER = 'offer',
  DEMAND = 'demand',
}

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Advertisement extends mixins(AlertMixin) {
  @Inject('advertisementService') private advertisementService: () => AdvertisementService;

  @Inject('advertisementTypeService') private advertisementTypeService: () => AdvertisementTypeService;

  @Inject('accountService') private accountService: () => AccountService;

  @Inject('companyService') private companyService: () => CompanyService;

  private hasAnyAuthorityValue = false;

  public itemsPerPage = 20;
  public queryCount: number = null;
  public page = 1;
  public previousPage = 1;
  public propOrder = 'id';
  public reverse = false;
  public totalItems = 0;

  public advertisements: IAdvertisement[] = [];
  public selectedAdvertisements: IAdvertisement[] = [];
  public company: ICompany = null;

  public isFetching = false;

  public activeTypeFilter = AdvertisementFilter.ALL;
  public filterAllButtonVariant = 'secondary';
  public filterOfferButtonVariant = 'outline-secondary';
  public filterDemandButtonVariant = 'outline-secondary';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.companyId) {
        vm.companyService()
          .find(to.params.companyId)
          .then(res => {
            vm.company = res;
            vm.retrieveAllAdvertisements();
          });
      }
    });
  }

  // public mounted(): void {
  //   this.retrieveAllAdvertisements();
  //   this.advertisementStatusService()
  //     .retrieve()
  //     .then(res => {
  //       this.advertisementStatuses = res.data;
  //     });
  // }

  public clear(): void {
    this.page = 1;
    this.retrieveAllAdvertisements();
  }

  public retrieveAllAdvertisements(): void {
    this.isFetching = true;

    const paginationQuery = {
      page: this.page - 1,
      size: this.itemsPerPage,
      sort: this.sort(),
    };
    this.advertisementService()
      .retrieveForCompany(this.company.id, AdvertisementStatus.ACTIVE, paginationQuery)
      .then(
        res => {
          this.advertisements = res.data;

          this.totalItems = Number(res.headers['x-total-count']);
          this.queryCount = this.totalItems;
          this.isFetching = false;

          if (this.activeTypeFilter === AdvertisementFilter.ALL) {
            this.showAllAdvertisements();
          } else if (this.activeTypeFilter === AdvertisementFilter.OFFER) {
            this.showOfferAdvertisements();
          } else if (this.activeTypeFilter === AdvertisementFilter.DEMAND) {
            this.showDemandAdvertisements();
          }
        },
        err => {
          this.isFetching = false;
        }
      );
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

  public sort(): Array<any> {
    const result = [this.propOrder + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.propOrder !== 'id') {
      result.push('id');
    }
    return result;
  }

  public loadPage(page: number): void {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  public transition(): void {
    this.retrieveAllAdvertisements();
  }

  public changeOrder(propOrder): void {
    this.propOrder = propOrder;
    this.reverse = !this.reverse;
    this.transition();
  }

  public getExpirationDate(advertisement: IAdvertisement): Date {
    const expirationDate = new Date(advertisement.activationDatetime);
    expirationDate.setMonth(expirationDate.getMonth() + Number(advertisement.duration.duration));

    return expirationDate;
  }

  public showAllAdvertisements(): void {
    this.selectedAdvertisements = this.advertisements;

    this.activeTypeFilter = AdvertisementFilter.ALL;
    this.filterAllButtonVariant = 'secondary';
    this.filterOfferButtonVariant = 'outline-secondary';
    this.filterDemandButtonVariant = 'outline-secondary';
  }

  public showOfferAdvertisements(): void {
    this.selectedAdvertisements = this.advertisements.filter(ad => ad.type.type === AdvertisementType.OFFER);

    this.activeTypeFilter = AdvertisementFilter.OFFER;
    this.filterAllButtonVariant = 'outline-secondary';
    this.filterOfferButtonVariant = 'secondary';
    this.filterDemandButtonVariant = 'outline-secondary';
  }

  public showDemandAdvertisements(): void {
    this.selectedAdvertisements = this.advertisements.filter(ad => ad.type.type === AdvertisementType.DEMAND);

    this.activeTypeFilter = AdvertisementFilter.DEMAND;
    this.filterAllButtonVariant = 'outline-secondary';
    this.filterOfferButtonVariant = 'outline-secondary';
    this.filterDemandButtonVariant = 'secondary';
  }
}
