<template>
    <div>
        <b-alert :show="dismissCountDown"
            dismissible
            :variant="alertType"
            @dismissed="dismissCountDown=0"
            @dismiss-count-down="countDownChanged">
            {{alertMessage}}
        </b-alert>
        <div v-if="company" class="d-flex mb-3">
            <img v-if="company.logo" :src="companyService().retrieveImage(company.logo.filename)"
                alt="company logo" 
                style="height: 100px; margin-right: 1rem">
            <h2 id="page-heading"
                class="jh-entity-heading"
                style="align-self: center; text-wrap: nowrap;"> 
                {{company.name}}
            </h2>
        </div>
        <br/>
        <div class="alert alert-warning" v-if="!isFetching && collaborations && collaborations.length === 0">
            <span v-text="$t('riportalApp.collaboration.home.notFound')">No collaborations found</span>
        </div>
        <div class="ml-3 mb-3" style="display: flex; align-items: center;">
            <h3 v-text="'Saradnje'" class="mr-3">Saradnje</h3>
            <div>
                <b-button :variant="filterAllButtonVariant" v-text="'Sve saradnje'" v-on:click="showAllCollaborations()">Cancel</b-button>
                <b-button :variant="filterOfferButtonVariant" v-text="'Oglašivač'" v-on:click="showOfferCollaborations()">Cancel</b-button>
                <b-button :variant="filterRequestButtonVariant" v-text="'Tražilac'" v-on:click="showRequestCollaborations()">Cancel</b-button>
            </div>
        </div>
        <div class="table-responsive" v-if="collaborations && collaborations.length > 0">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th v-on:click="changeOrder('datetime')"><span v-text="$t('riportalApp.collaboration.datetime')">Datetime</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'datetime'"></jhi-sort-indicator></th>
                    <!-- <th v-on:click="changeOrder('commentOffer')"><span v-text="$t('riportalApp.collaboration.commentOffer')">Comment Offer</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'commentOffer'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('commentRequest')"><span v-text="$t('riportalApp.collaboration.commentRequest')">Comment Request</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'commentRequest'"></jhi-sort-indicator></th> -->
                    <th v-on:click="changeOrder('companyOffer.id')"><span v-text="$t('riportalApp.collaboration.companyOffer')">Company Offer</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'companyOffer.id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('companyRequest.id')"><span v-text="$t('riportalApp.collaboration.companyRequest')">Company Request</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'companyRequest.id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('advertisement.name')"><span v-text="$t('riportalApp.collaboration.advertisement')">Advertisement</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'advertisement.name'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('advertisement.type.type')"><span v-text="'Type'">Advertisement</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'advertisement.type.type'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('advertisement.kind.kind')"><span v-text="'Kind'">Advertisement</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'advertisement.kind.kind'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('advertisement.budget')"><span v-text="'Budget'">Advertisement</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'advertisement.budget'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('advertisement.duration.duration')"><span v-text="'Duration'">Advertisement</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'advertisement.duration.duration'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('ratingOffer.id')"><span v-text="$t('riportalApp.collaboration.ratingOffer')">Rating Offer</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'ratingOffer.id'"></jhi-sort-indicator></th>
                    <th v-on:click="changeOrder('ratingRequest.id')"><span v-text="$t('riportalApp.collaboration.ratingRequest')">Rating Request</span> <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'ratingRequest.id'"></jhi-sort-indicator></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="collaboration in collaborations"
                    :key="collaboration.id">
                    <!-- <td>
                        <router-link :to="{name: 'CollaborationView', params: {collaborationId: collaboration.id}}">{{collaboration.id}}</router-link>
                    </td> -->
                    <td>{{collaboration.datetime ? $d(Date.parse(collaboration.datetime), 'short') : ''}}</td>
                    <!-- <td>{{collaboration.commentOffer}}</td>
                    <td>{{collaboration.commentRequest}}</td> -->
                    <td>
                        <div v-if="collaboration.companyOffer">
                            <router-link :to="{name: 'CompanyView', params: {companyId: collaboration.companyOffer.id}}">{{collaboration.companyOffer.name}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="collaboration.companyRequest">
                            <router-link :to="{name: 'CompanyView', params: {companyId: collaboration.companyRequest.id}}">{{collaboration.companyRequest.name}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="collaboration.advertisement">
                            <router-link :to="{name: 'AdvertisementView', params: {advertisementId: collaboration.advertisement.id}}">{{collaboration.advertisement.title}}</router-link>
                        </div>
                    </td>
                    <td>{{collaboration.advertisement.type.type}}</td>
                    <td>{{collaboration.advertisement.kind.kind}}</td>
                    <td>{{collaboration.advertisement.budget}}</td>
                    <td>{{collaboration.advertisement.duration.duration}}</td>
                    <td>
                        <div v-if="collaboration.ratingOffer">
                            <router-link :to="{name: 'CollaborationRatingView', params: {collaborationRatingId: collaboration.ratingOffer.id}}">{{collaboration.ratingOffer.id}}</router-link>
                        </div>
                    </td>
                    <td>
                        <div v-if="collaboration.ratingRequest">
                            <router-link :to="{name: 'CollaborationRatingView', params: {collaborationRatingId: collaboration.ratingRequest.id}}">{{collaboration.ratingRequest.id}}</router-link>
                        </div>
                    </td>
                    <td class="text-right">
                        <div class="btn-group">
                            <router-link :to="{name: 'CollaborationView', params: {collaborationId: collaboration.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'CollaborationEdit', params: {collaborationId: collaboration.id}}"  tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(collaboration)"
                                   variant="danger"
                                   class="btn btn-sm"
                                   v-b-modal.removeEntity>
                                <font-awesome-icon icon="times"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                            </b-button>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <b-modal ref="removeEntity" id="removeEntity" >
            <span slot="modal-title"><span id="riportalApp.collaboration.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-collaboration-heading" v-text="$t('riportalApp.collaboration.delete.question', {'id': removeId})">Are you sure you want to delete this Collaboration?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-collaboration" v-text="$t('entity.action.delete')" v-on:click="removeCollaboration()">Delete</button>
            </div>
        </b-modal>
        <div v-show="collaborations && collaborations.length > 0">
            <div class="row justify-content-center">
                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
            </div>
            <div class="row justify-content-center">
                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./company-collaborations.component.ts">
</script>
