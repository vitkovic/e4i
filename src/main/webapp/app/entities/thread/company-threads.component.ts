import { mixins } from 'vue-class-component';
import AlertMixin from '@/shared/alert/alert.mixin';
import Vue2Filters from 'vue2-filters';
import { Component, Vue, Inject } from 'vue-property-decorator';

import { IThread } from '@/shared/model/thread.model';
import { IMessage, Message } from '@/shared/model/message.model';
import { ICompany } from '@/shared/model/company.model';
import { IAdvertisement } from '@/shared/model/advertisement.model';
import { IPortalUser } from '@/shared/model/portal-user.model';

import ThreadService from './thread.service';
import CompanyService from '@/entities/company.service';
import MessageService from '@/entities/message.service';
import PortalUserService from '../../entities/portal-user/portal-user.service';

enum ThreadsFilter {
  ALL = 'all',
  SENDER = 'sender',
  RECEIVER = 'receiver',
}

interface IThreadDTO {
  id: Number;
  subject: string;
  companySender: ICompany;
  companyReceiver: ICompany;
  advertisement: IAdvertisement;
  messageCount: Number;
  lastMessageDatetime: Date;
  lastMessageContent: string;
}

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Thread extends mixins(AlertMixin) {
  @Inject('threadService') private threadService: () => ThreadService;
  @Inject('companyService') private companyService: () => CompanyService;
  @Inject('messageService') private messageService: () => MessageService;
  @Inject('portalUserService') private portalUserService: () => PortalUserService;

  private removeId: number = null;
  public itemsPerPage = 20;
  public queryCount: number = null;
  public page = 1;
  public previousPage = 1;
  public propOrder = 'id';
  public reverse = false;
  public totalItems = 0;
  public newMessageText = '';

  private portalUser: IPortalUser = null;
  public threads: IThread[] = [];
  public threadsDTO: IThreadDTO[] = [];
  public company: ICompany = null;
  public messages: IMessage[] = [];
  public newMessage: IMessage = new Message();

  public isFetching = false;

  public activeThreadFilter = ThreadsFilter.ALL;
  public filterAllButtonVariant = 'secondary';
  public filterSenderButtonVariant = 'outline-secondary';
  public filterReceiverButtonVariant = 'outline-secondary';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.companyId) {
        vm.companyService()
          .find(to.params.companyId)
          .then(res => {
            vm.company = res;
            vm.getPortalUser();
            vm.retrieveThreads();
          });
      }
    });
  }

  // public mounted(): void {
  //   this.retrieveAllThreads();
  // }

  public clear(): void {
    this.page = 1;
    this.retrieveThreads();
  }

  public retrieveThreads(): void {
    this.isFetching = true;

    const paginationQuery = {
      page: this.page - 1,
      size: this.itemsPerPage,
      sort: this.sort(),
    };

    if (this.activeThreadFilter === ThreadsFilter.ALL) {
      this.threadService()
        .retrieveByCompany(this.company.id, paginationQuery)
        .then(
          res => {
            this.threadsDTO = res.data;
            this.totalItems = Number(res.headers['x-total-count']);
            this.queryCount = this.totalItems;
            this.isFetching = false;
          },
          err => {
            this.isFetching = false;
          }
        );
    } else if (this.activeThreadFilter === ThreadsFilter.SENDER) {
      this.threadService()
        .retrieveByCompanySender(this.company.id, paginationQuery)
        .then(
          res => {
            this.threadsDTO = res.data;
            this.totalItems = Number(res.headers['x-total-count']);
            this.queryCount = this.totalItems;
            this.isFetching = false;
          },
          err => {
            this.isFetching = false;
          }
        );
    } else if (this.activeThreadFilter === ThreadsFilter.RECEIVER) {
      this.threadService()
        .retrieveByCompanyReceiver(this.company.id, paginationQuery)
        .then(
          res => {
            this.threadsDTO = res.data;
            this.totalItems = Number(res.headers['x-total-count']);
            this.queryCount = this.totalItems;
            this.isFetching = false;
          },
          err => {
            this.isFetching = false;
          }
        );
    }
  }

  public getPortalUser() {
    const user = this.$store.getters.account;
    if (user) {
      this.portalUserService()
        .findByUserId(user.id)
        .then(res => {
          this.portalUser = res;
          if (this.portalUser.company?.id === this.company.id) {
          }
        });
    }
  }

  public prepareRemove(instance: IThread): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeThread(): void {
    this.threadService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('riportalApp.thread.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveThreads();
        this.closeDialog();
      });
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
    this.retrieveThreads();
  }

  public changeOrder(propOrder): void {
    this.propOrder = propOrder;
    this.reverse = !this.reverse;
    this.transition();
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }

  public showAllThreads(): void {
    this.activeThreadFilter = ThreadsFilter.ALL;
    this.retrieveThreads();

    this.filterAllButtonVariant = 'secondary';
    this.filterSenderButtonVariant = 'outline-secondary';
    this.filterReceiverButtonVariant = 'outline-secondary';
  }

  public showSenderThreads(): void {
    this.activeThreadFilter = ThreadsFilter.SENDER;
    this.retrieveThreads();

    this.filterAllButtonVariant = 'outline-secondary';
    this.filterSenderButtonVariant = 'secondary';
    this.filterReceiverButtonVariant = 'outline-secondary';
  }

  public showReceiverThreads(): void {
    this.activeThreadFilter = ThreadsFilter.RECEIVER;
    this.retrieveThreads();

    this.filterAllButtonVariant = 'outline-secondary';
    this.filterSenderButtonVariant = 'outline-secondary';
    this.filterReceiverButtonVariant = 'secondary';
  }

  public getMessages(thread: IThreadDTO) {
    if (thread?.id) {
      this.messageService()
        .getAllByThreadId(thread.id)
        .then(res => {
          this.messages = res;
        });
    }
  }

  public buildThreadDisplayString(thread: IThreadDTO): String {
    const CHAR_LIMIT = 70;
    const subjectLength = thread.subject.length;
    let displayString = '';

    if (!thread?.lastMessageContent) {
      return displayString;
    }

    if (thread.lastMessageContent.length + subjectLength <= CHAR_LIMIT) {
      return ' - ' + thread.lastMessageContent;
    }

    displayString = ' - ' + thread.lastMessageContent.substring(0, CHAR_LIMIT - subjectLength) + '...';

    return displayString;
  }

  public sendMessage(thread: IThreadDTO) {
    const formData = new FormData();
    formData.append('content', '' + this.newMessageText);
    formData.append('senderId', '' + this.portalUser.id);
    formData.append('threadId', '' + thread.id);

    this.messageService()
      .createMessageInThread(formData)
      .then(res => {
        this.retrieveThreads();
        this.getMessages(thread);
      });

    this.newMessageText = '';
  }

  public deleteMessage(message: IMessage, thread: IThreadDTO) {
    if (!(message?.id && this.portalUser && thread?.companySender?.id && thread?.companyReceiver?.id)) {
      return;
    }

    const companySenderId = thread.companySender.id;
    const companyReceiverId = thread.companyReceiver.id;
    const portalUserCompanyId = this.portalUser.company.id;

    // Odredjivanje za koga treba da se sakrije poruka.
    if (portalUserCompanyId === companySenderId) {
      message.isDeletedSender = true;
    } else if (portalUserCompanyId === companyReceiverId) {
      message.isDeletedReceiver = true;
    }

    if (message.isDeletedSender === message.isDeletedReceiver) {
      this.messageService()
        .delete(message.id)
        .then(res => {
          const notificatonMessage = 'Poruka je obrisana!';
          this.$notify({
            text: notificatonMessage,
          });
          this.retrieveThreads();
          this.getMessages(thread);
          console.log(this.messages);
        });
    } else {
      this.messageService()
        .update(message)
        .then(res => {
          const notificatonMessage = 'Poruka je obrisana!';
          this.$notify({
            text: notificatonMessage,
          });
          this.retrieveThreads();
          this.getMessages(thread);
        });
    }
  }
}
