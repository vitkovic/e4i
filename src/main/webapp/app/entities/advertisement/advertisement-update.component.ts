import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, minLength, maxLength, minValue, maxValue, requiredIf } from 'vuelidate/lib/validators';
import format from 'date-fns/format';
import parse from 'date-fns/parse';
import parseISO from 'date-fns/parseISO';
import { DATE_TIME_LONG_FORMAT } from '@/shared/date/filters';

import AdvertisementSupporterService from '../advertisement-supporter/advertisement-supporter.service';
import { IAdvertisementSupporter } from '@/shared/model/advertisement-supporter.model';

import CollaborationService from '../collaboration/collaboration.service';
import { ICollaboration } from '@/shared/model/collaboration.model';

import MeetingService from '../meeting/meeting.service';
import { IMeeting } from '@/shared/model/meeting.model';

import DocumentService from '../document/document.service';
import { IDocument } from '@/shared/model/document.model';

import PortalUserService from '../portal-user/portal-user.service';
import { IPortalUser } from '@/shared/model/portal-user.model';

import CompanyService from '../company/company.service';
import { ICompany } from '@/shared/model/company.model';

import AdvertisementStatusService from '../advertisement-status/advertisement-status.service';
import { IAdvertisementStatus } from '@/shared/model/advertisement-status.model';

import AdvertisementDurationService from '../advertisement-duration/advertisement-duration.service';
import { IAdvertisementDuration } from '@/shared/model/advertisement-duration.model';

import AdvertisementTypeService from '../advertisement-type/advertisement-type.service';
import { IAdvertisementType } from '@/shared/model/advertisement-type.model';

import AdvertisementKindService from '../advertisement-kind/advertisement-kind.service';
import { IAdvertisementKind } from '@/shared/model/advertisement-kind.model';

import AdvertisementSubsubcategoryService from '../advertisement-subsubcategory/advertisement-subsubcategory.service';
import { IAdvertisementSubsubcategory } from '@/shared/model/advertisement-subsubcategory.model';

import ThreadService from '../thread/thread.service';
import { IThread } from '@/shared/model/thread.model';

import AlertService from '@/shared/alert/alert.service';
import { IAdvertisement, Advertisement } from '@/shared/model/advertisement.model';
import AdvertisementService from './advertisement.service';

import AccountService from '@/account/account.service';

const validations: any = {
  advertisement: {
    createdAt: {},
    modifiedAt: {},
    company: {
      required: requiredIf(function () {
        return this.hasAnyAuthority('ROLE_ADMIN');
      }),
    },
    activationDatetime: {
      // required,
    },
    title: {
      required,
    },
    description: {
      required,
    },
    budget: {},
    conditions: {},
  },
};

interface ImageBlob extends Blob {
  name: string;
  type: 'image/*';
}

interface DocumentBlob extends Blob {
  name: string;
  type: 'application/pdf';
}

@Component({
  validations,
})
export default class AdvertisementUpdate extends Vue {
  @Inject('alertService') private alertService: () => AlertService;
  @Inject('advertisementService') private advertisementService: () => AdvertisementService;
  public advertisement: IAdvertisement = new Advertisement();

  @Inject('advertisementSupporterService') private advertisementSupporterService: () => AdvertisementSupporterService;

  public advertisementSupporters: IAdvertisementSupporter[] = [];

  @Inject('collaborationService') private collaborationService: () => CollaborationService;

  public collaborations: ICollaboration[] = [];

  @Inject('meetingService') private meetingService: () => MeetingService;

  public meetings: IMeeting[] = [];

  @Inject('documentService') private documentService: () => DocumentService;

  public documents: IDocument[] = [];

  @Inject('portalUserService') private portalUserService: () => PortalUserService;

  public portalUsers: IPortalUser[] = [];

  @Inject('companyService') private companyService: () => CompanyService;

  public companies: ICompany[] = [];

  @Inject('advertisementStatusService') private advertisementStatusService: () => AdvertisementStatusService;

  public advertisementStatuses: IAdvertisementStatus[] = [];

  @Inject('advertisementDurationService') private advertisementDurationService: () => AdvertisementDurationService;

  public advertisementDurations: IAdvertisementDuration[] = [];

  @Inject('advertisementTypeService') private advertisementTypeService: () => AdvertisementTypeService;

  public advertisementTypes: IAdvertisementType[] = [];

  @Inject('advertisementKindService') private advertisementKindService: () => AdvertisementKindService;

  public advertisementKinds: IAdvertisementKind[] = [];

  @Inject('advertisementSubsubcategoryService') private advertisementSubsubcategoryService: () => AdvertisementSubsubcategoryService;

  public advertisementSubsubcategories: IAdvertisementSubsubcategory[] = [];

  @Inject('threadService') private threadService: () => ThreadService;

  @Inject('accountService') private accountService: () => AccountService;

  public threads: IThread[] = [];
  public isSaving = false;
  public currentLanguage = '';
  private hasAnyAuthorityValue = false;

  public formImages: ImageBlob[] = [];
  public imageFiles: ImageBlob[] = [];
  public formDocuments: DocumentBlob[] = [];
  public documentFiles: DocumentBlob[] = [];
  public placeholdertext = '';
  public browseText = '';
  public showDocumentsSection = false;
  public selectedImageId: number | null = null;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.advertisementId) {
        vm.retrieveAdvertisement(to.params.advertisementId);
      }
      vm.initRelationships();
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
    this.advertisement.documents = [];
  }

  public save(): void {
    this.isSaving = true;
    if (this.advertisement.id) {
      // Dodeljivannje default vrednosti
      this.advertisement.modifiedAt = new Date();

      this.advertisementService()
        .update(this.advertisement)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('riportalApp.advertisement.updated', { param: param.id });
          this.alertService().showAlert(message, 'info');
          const user = this.$store.getters.account;
          this.advertisementService().updateModifiedBy(this.advertisement.id, user.id);
        });
    } else {
      // Dodeljivannje default vrednosti
      this.advertisement.createdAt = new Date();
      this.advertisement.activationDatetime = this.advertisement.createdAt;
      this.advertisement.status = this.advertisementStatuses.filter(status => status.status === 'Активан')[0];

      this.advertisementService()
        .create(this.advertisement)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('riportalApp.advertisement.created', { param: param.id });
          this.alertService().showAlert(message, 'success');
          const user = this.$store.getters.account;
          this.advertisementService().updateCreatedBy(param.id, user.id);
        });
    }
  }

  public convertDateTimeFromServer(date: Date): string {
    if (date) {
      return format(date, DATE_TIME_LONG_FORMAT);
    }
    return null;
  }

  public updateInstantField(field, event) {
    if (event.target.value) {
      this.advertisement[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.advertisement[field] = null;
    }
  }

  public updateZonedDateTimeField(field, event) {
    if (event.target.value) {
      this.advertisement[field] = parse(event.target.value, DATE_TIME_LONG_FORMAT, new Date());
    } else {
      this.advertisement[field] = null;
    }
  }

  public retrieveAdvertisement(advertisementId): void {
    this.advertisementService()
      .find(advertisementId)
      .then(res => {
        res.createdAt = new Date(res.createdAt);
        res.modifiedAt = new Date(res.modifiedAt);
        res.activationDatetime = new Date(res.activationDatetime);
        this.advertisement = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.advertisementSupporterService()
      .retrieve()
      .then(res => {
        this.advertisementSupporters = res.data;
      });
    this.collaborationService()
      .retrieve()
      .then(res => {
        this.collaborations = res.data;
      });
    this.meetingService()
      .retrieve()
      .then(res => {
        this.meetings = res.data;
      });
    this.documentService()
      .retrieve()
      .then(res => {
        this.documents = res.data;
      });
    this.portalUserService()
      .retrieve()
      .then(res => {
        this.portalUsers = res.data;
      });
    this.portalUserService()
      .retrieve()
      .then(res => {
        this.portalUsers = res.data;
      });
    this.companyService()
      .retrieve()
      .then(res => {
        this.companies = res.data;
      });
    this.advertisementStatusService()
      .retrieve()
      .then(res => {
        this.advertisementStatuses = res.data;
      });
    this.advertisementDurationService()
      .retrieve()
      .then(res => {
        this.advertisementDurations = res.data;
      });
    this.advertisementTypeService()
      .retrieve()
      .then(res => {
        this.advertisementTypes = res.data;
      });
    this.advertisementKindService()
      .retrieve()
      .then(res => {
        this.advertisementKinds = res.data;
      });
    this.advertisementSubsubcategoryService()
      .retrieve()
      .then(res => {
        this.advertisementSubsubcategories = res.data;
      });
    this.threadService()
      .retrieve()
      .then(res => {
        this.threads = res.data;
      });
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

  public getSelected(selectedVals, option): any {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }

  public formatNames(files): string {
    if (files.length === 1) {
      return files[0].name;
    } else {
      // const out = '' + this.$t('riportalApp.researchInfrastructure.filesSelected', { param: files.length });
      const out = 'Files selected: ' + files.length;
      return out;
    }
    // return files.length === 1 ? files[0].name : `${files.length} files selected`;
  }

  public saveImages(): void {
    if (this.imageFiles.length === 0) {
      const por = this.$t('riportalApp.researchInfrastructure.errors.fileTypeNotMatch');
      this.$notify({ text: JSON.stringify(por).replace(/["]/g, ''), type: 'error', duration: 10000 });
      return;
    }
    this.isSaving = true;
    const formData = new FormData();

    for (let i = 0; i < this.imageFiles.length; i++) {
      formData.append('files', this.imageFiles[i]);
    }

    formData.append('id', '' + this.advertisement.id);

    this.advertisementService()
      .uploadImages(formData)
      .then(res => {
        this.isSaving = false;
        this.imageFiles = [];
        // this.company.documents = res;
        this.retrieveAdvertisement(this.advertisement.id);
      })
      .catch(error => {
        this.isSaving = false;
        if (error.response.status === 400 && error.response.data.type === 'INFRASTRUCTURE_IMAGES_LIMIT') {
          const por = this.$t('riportalApp.researchInfrastructure.errors.infrastructureImagesLimit');
          this.$notify({ text: JSON.stringify(por).replace(/["]/g, ''), type: 'error', duration: 10000 });
        }
      });
  }

  public saveDocuments(): void {
    if (this.documentFiles.length === 0) {
      const por = this.$t('riportalApp.researchInfrastructure.errors.fileTypeNotMatch');
      this.$notify({ text: JSON.stringify(por).replace(/["]/g, ''), type: 'error', duration: 10000 });
      return;
    }
    this.isSaving = true;
    const formData = new FormData();

    for (let i = 0; i < this.documentFiles.length; i++) {
      formData.append('files', this.documentFiles[i]);
    }

    formData.append('id', '' + this.advertisement.id);

    this.advertisementService()
      .uploadDocuments(formData)
      .then(res => {
        this.isSaving = false;
        this.documentFiles = [];
        // this.company.documents = res;
        this.retrieveAdvertisement(this.advertisement.id);
      })
      .catch(error => {
        this.isSaving = false;
        if (error.response.status === 400 && error.response.data.type === 'INFRASTRUCTURE_IMAGES_LIMIT') {
          const por = this.$t('riportalApp.researchInfrastructure.errors.infrastructureImagesLimit');
          this.$notify({ text: JSON.stringify(por).replace(/["]/g, ''), type: 'error', duration: 10000 });
        }
      });
  }

  public appendImageFiles(): void {
    for (const formImage of this.formImages) {
      if (this.imageFiles.filter(image => image.name === formImage.name).length === 0) {
        this.imageFiles.push(formImage);
      }
    }
  }

  public appendDocumentFiles(): void {
    for (const formDocument of this.formDocuments) {
      if (this.documentFiles.filter(document => document.name === formDocument.name).length === 0) {
        this.documentFiles.push(formDocument);
      }
    }
  }

  public removeImage(filename: string): void {
    for (let i = 0; i < this.imageFiles.length; i++) {
      if (this.imageFiles[i].name === filename) {
        this.imageFiles.splice(i, 1);
        return;
      }
    }
  }

  public removeDocument(filename: string): void {
    for (let i = 0; i < this.documentFiles.length; i++) {
      if (this.documentFiles[i].name === filename) {
        this.documentFiles.splice(i, 1);
        return;
      }
    }
  }

  public deleteImage(): void {
    if (this.selectedImageId !== null) {
      const advertisementId = this.advertisement.id;
      this.advertisementService()
        .deleteImage(advertisementId, this.selectedImageId)
        .then(res => {
          this.isSaving = false;
          this.retrieveAdvertisement(this.advertisement.id);
          // Close the modal after successful deletion
          this.closeDeleteImageDialog();
        });
    }
  }

  public openDeleteImageModal(imageId: number): void {
    this.selectedImageId = imageId;
    // Show the modal
    // (this.$refs.deleteImageModal as any).show();
  }

  public deleteDocument(documentId: number): void {
    const advertisementId = this.advertisement.id;
    this.advertisementService()
      .deleteDocument(advertisementId, documentId)
      .then(res => {
        this.isSaving = false;
        this.retrieveAdvertisement(this.advertisement.id);
        this.closeDeleteImageDialog();
      });
  }

  public closeDeleteImageDialog(): void {
    this.selectedImageId = null;
    (this.$refs.deleteImageModal as any).hide();
  }
}
