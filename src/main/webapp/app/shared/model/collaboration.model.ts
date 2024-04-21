import { ICompany } from '@/shared/model/company.model';
import { IAdvertisement } from '@/shared/model/advertisement.model';
import { ICollaborationRating } from '@/shared/model/collaboration-rating.model';

export interface ICollaboration {
  id?: number;
  datetime?: Date;
  commentOffer?: string;
  commentRequest?: string;
  companyOffer?: ICompany;
  companyRequest?: ICompany;
  advertisement?: IAdvertisement;
  ratingOffer?: ICollaborationRating;
  ratingRequest?: ICollaborationRating;
}

export class Collaboration implements ICollaboration {
  constructor(
    public id?: number,
    public datetime?: Date,
    public commentOffer?: string,
    public commentRequest?: string,
    public companyOffer?: ICompany,
    public companyRequest?: ICompany,
    public advertisement?: IAdvertisement,
    public ratingOffer?: ICollaborationRating,
    public ratingRequest?: ICollaborationRating
  ) {}
}
