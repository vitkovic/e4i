<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="riportalApp.company.home.createOrEditLabel" v-text="$t('riportalApp.company.home.createOrEditLabel')">Create or edit a Company</h2>
                <div>
                    <div class="form-group" v-if="company.id && authenticated && hasAnyAuthority('ROLE_ADMIN')">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="company.id" readonly />
                    </div>
                    <div class="form-group" v-if="authenticated && hasAnyAuthority('ROLE_ADMIN')">
                        <label class="form-control-label" v-text="$t('riportalApp.company.createdAt')" for="company-createdAt">Created At</label>
                        <div class="d-flex">
                            <input id="company-createdAt" type="datetime-local" class="form-control" name="createdAt" :class="{'valid': !$v.company.createdAt.$invalid, 'invalid': $v.company.createdAt.$invalid }"
                            
                            :value="convertDateTimeFromServer($v.company.createdAt.$model)"
                            @change="updateInstantField('createdAt', $event)" disabled="true"/>
                        </div>
                    </div>
                    <div class="form-group" v-if="authenticated && hasAnyAuthority('ROLE_ADMIN')">
                        <label class="form-control-label" v-text="$t('riportalApp.company.createdBy')" for="company-createdBy">Created By</label>
                        <input v-if="company.createdBy != null" type="text" class="form-control" id="id" name="id"
                               v-model="company.createdBy.firstname" readonly />
                        <input v-else type="text" class="form-control" id="id" name="id" readonly>       
                        <!-- <select class="form-control" id="company-createdBy" name="createdBy" v-model="company.createdBy" disabled="true">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="company.createdBy && portalUserOption.id === company.createdBy.id ? company.createdBy : portalUserOption" v-for="portalUserOption in portalUsers" :key="portalUserOption.id">{{portalUserOption.id}}</option>
                        </select> -->
                    </div>
                    <div class="form-group" v-if="authenticated && hasAnyAuthority('ROLE_ADMIN')">
                        <label class="form-control-label" v-text="$t('riportalApp.company.modifiedAt')" for="company-modifiedAt">Modified At</label>
                        <div class="d-flex">
                            <input id="company-modifiedAt" type="datetime-local" class="form-control" name="modifiedAt" :class="{'valid': !$v.company.modifiedAt.$invalid, 'invalid': $v.company.modifiedAt.$invalid }"
                            
                            :value="convertDateTimeFromServer($v.company.modifiedAt.$model)"
                            @change="updateInstantField('modifiedAt', $event)" disabled="true"/>
                        </div>
                    </div>
                    <div class="form-group" v-if="authenticated && hasAnyAuthority('ROLE_ADMIN')">
                        <label class="form-control-label" v-text="$t('riportalApp.company.modifiedBy')" for="company-modifiedBy">Modified By</label>
                        <input v-if="company.modifiedBy != null" type="text" class="form-control" id="id" name="id"
                               v-model="company.modifiedBy.firstname" readonly />
                        <input v-else type="text" class="form-control" id="id" name="id" readonly>
                        <!-- <select class="form-control" id="company-modifiedBy" name="modifiedBy" v-model="company.modifiedBy" disabled="true">
                            <option v-bind:value="company.modifiedBy"></option>
                            <option v-bind:value="company.modifiedBy && portalUserOption.id === company.modifiedBy.id ? company.modifiedBy : portalUserOption" v-for="portalUserOption in portalUsers" :key="portalUserOption.id">{{portalUserOption.firstname}}</option>
                        </select> -->
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('riportalApp.company.rsnisId')" for="company-rsnisId">Rsnis Id</label>
                        <input type="number" class="form-control" name="rsnisId" id="company-rsnisId"
                            :class="{'valid': !$v.company.rsnisId.$invalid, 'invalid': $v.company.rsnisId.$invalid }" v-model.number="$v.company.rsnisId.$model"  required/>
                        <div v-if="$v.company.rsnisId.$anyDirty && $v.company.rsnisId.$invalid">
                            <small class="form-text text-danger" v-if="!$v.company.rsnisId.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.company.rsnisId.numeric" v-text="$t('entity.validation.number')">
                                This field should be a number.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('riportalApp.company.aprId')" for="company-aprId">Apr Id</label>
                        <input type="number" class="form-control" name="aprId" id="company-aprId"
                            :class="{'valid': !$v.company.aprId.$invalid, 'invalid': $v.company.aprId.$invalid }" v-model.number="$v.company.aprId.$model"  required/>
                        <div v-if="$v.company.aprId.$anyDirty && $v.company.aprId.$invalid">
                            <small class="form-text text-danger" v-if="!$v.company.aprId.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                            <small class="form-text text-danger" v-if="!$v.company.aprId.numeric" v-text="$t('entity.validation.number')">
                                This field should be a number.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('riportalApp.company.name')" for="company-name">Name</label>
                        <input type="text" class="form-control" name="name" id="company-name"
                            :class="{'valid': !$v.company.name.$invalid, 'invalid': $v.company.name.$invalid }" v-model="$v.company.name.$model"  required/>
                        <div v-if="$v.company.name.$anyDirty && $v.company.name.$invalid">
                            <small class="form-text text-danger" v-if="!$v.company.name.required" v-text="$t('entity.validation.required')">
                                This field is required.
                            </small>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('riportalApp.company.about')" for="company-about">About</label>
                        <textarea type="text" class="form-control" name="about" id="company-about"
                            :class="{'valid': !$v.company.about.$invalid, 'invalid': $v.company.about.$invalid }" v-model="$v.company.about.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('riportalApp.company.fields')" for="company-fields">Fields</label>
                        <input type="text" class="form-control" name="fields" id="company-fields"
                            :class="{'valid': !$v.company.fields.$invalid, 'invalid': $v.company.fields.$invalid }" v-model="$v.company.fields.$model" />
                    </div>
                    <!-- <div class="form-group">
                        <label v-text="$t('riportalApp.company.badge')" for="company-badge">Badge</label>
                        <select class="form-control" id="company-badge" multiple name="badge" v-model="company.badges">
                            <option v-bind:value="getSelected(company.badges, badgeOption)" v-for="badgeOption in badges" :key="badgeOption.id">{{badgeOption.id}}</option>
                        </select>
                    </div> -->

                    <div class="documents-section" v-show="showDocumentsSection">
                    <hr>
                    <div class="form-group">
                        <label class="form-control-label position-relative" v-text="'Upload logo'"></label>   
                        <b-form-file style="margin-bottom:30px"
                            v-model="imageLogo"                            
                            :state="Boolean(imageLogo)"
                            :placeholder="placeholdertext"
                            drop-placeholder="Drop file here..."
                            :browstext="browseText"
                            accept=".jpg, .jpeg, .png, .svg"
                            :file-name-formatter="formatNames"
                        >        
                        </b-form-file>
                        <div v-if="company.logo">
                            <p>Current logo:</p>
                            <div>
                                <img :src="companyService().retrieveImage(company.logo.filename)" width="50"/>
                                {{ company.logo.filename }}
                                <button 
                                    type="button" 
                                    class="btn btn-danger"        
                                    v-on:click="deleteLogo()"> 
                                    <span v-text="'Delete'">Save</span>
                                </button>
                            </div>
                        </div>
                        <div v-else>There is no logo found</div>
                        <div v-if="imageLogo != null">
                            <p>New logo:</p>
                            {{ imageLogo.name }}
                            <button 
                                type="button" 
                                class="btn btn-danger" 
                                v-on:click="removeLogo()"> 
                                <span v-text="'Remove'">Save</span>
                            </button>
                        </div>
                    </div>
                    <b-row>
                        <b-col sm="11">
                            <button 
                                v-if="imageLogo != null"
                                type="button" 
                                id="save-entity"  
                                class="btn btn-primary mb-5" 
                                v-on:click="saveCompanyLogo()">
                                <span v-text="'Save Logo'">Save</span>
                            </button>
                        </b-col>
                    </b-row>
                    <hr>
                    <div class="form-group">
                                <label class="form-control-label position-relative" v-text="'Upload images'"></label>   
                                <b-form-file  style="margin-bottom:30px"
                                    v-model="formImages"
                                    @input="appendImageFiles()"                            
                                    :state="Boolean(formImages)"
                                    :placeholder="placeholdertext"
                                    multiple
                                    drop-placeholder="Drop file here..."
                                    :browstext="browseText"
                                    accept=".jpg, .jpeg, .png, .svg"
                                    :file-name-formatter="formatNames"
                                >                                                                        
                                </b-form-file>
                                <ul>
                                    <p>Current images:</p>
                                    <div v-for="document in company.documents">
                                        <li v-if="document.type.type == 'image'">
                                            <img :src="companyService().retrieveImage(document.filename)" width="50"/>
                                            {{ document.filename }}
                                            <button 
                                                type="button" 
                                                class="btn btn-danger"        v-on:click="deleteImage(document.id)"> 
                                                <span v-text="'Delete'">Save</span>
                                            </button>
                                        </li>
                                    </div>
                                </ul>
                                <ul>
                                    <p v-if="imageFiles.length > 0">New images:</p>
                                    <li v-for="image in imageFiles">
                                        {{ image.name }}
                                        <button type="button" class="btn btn-danger" v-on:click="removeImage(image.name)"> 
                                            <span v-text="'Remove'">Save</span>
                                        </button>
                                    </li>

                                </ul>
                        
                            </div>
                    <b-row>
                        <b-col sm="11">
                            <button 
                                v-if="imageFiles.length > 0"
                                type="button" 
                                id="save-entity" 
                                class="btn btn-primary mb-5" 
                                v-on:click="saveImages()">
                                <span v-text="'Save Images'">Save</span>
                            </button>
                        </b-col>
                    </b-row>
                    <hr>
                    <div class="form-group">
                                <label class="form-control-label position-relative" v-text="'Upload documents'"></label>   
                                <b-form-file  style="margin-bottom:30px"
                                    v-model="formDocuments"           
                                    @input="appendDocumentFiles()"                       
                                    :state="Boolean(formDocuments)"
                                    :placeholder="placeholdertext"
                                    multiple
                                    drop-placeholder="Drop file here..."
                                    :browstext="browseText"
                                    accept="application/pdf"
                                    :file-name-formatter="formatNames"
                                >                                                                        
                                </b-form-file>
                                <ul>
                                    <p>Current documents:</p>
                                    <div v-for="document in company.documents">
                                        <li v-if="document.type.type == 'document'">
                                            <a class="text-info" 
                                            :href="companyService().retrieveDocument(document.filename)" 
                                            target="_blank" 
                                            title="Preuzmite dokument">{{ document.filename }}
                                            </a>
                                            <button 
                                                type="button" 
                                                class="btn btn-danger"        v-on:click="deleteDocument(document.id)"> 
                                                <span v-text="'Delete'">Save</span>
                                            </button>
                                        </li>
                                    </div>
                                </ul>
                                <ul>
                                    <p v-if="documentFiles.length > 0">New documents:</p>
                                    <li v-for="document in documentFiles">
                                        {{ document.name }}
                                        <button type="button" class="btn btn-danger" v-on:click="removeDocument(document.name)"> 
                                            <span v-text="'Remove'">Save</span>
                                        </button>
                                    </li>
                                </ul>
                        
                            </div>
                    <b-row>
                        <b-col sm="11">
                            <button 
                                v-if="documentFiles.length > 0"
                                type="button" 
                                id="save-entity" 
                                class="btn btn-primary mb-5" 
                                v-on:click="saveCompanyDocuments()">
                                <span v-text="'Save Documents'">Save</span>
                            </button>
                        </b-col>
                    </b-row>

                    <!-- <div class="form-group">
                        <label v-text="$t('riportalApp.company.document')" for="company-document">Document</label>
                        <select class="form-control" id="company-document" multiple name="document" v-model="company.documents">
                            <option v-bind:value="getSelected(company.documents, documentOption)" v-for="documentOption in documents" :key="documentOption.id">{{documentOption.id}}</option>
                        </select>
                    </div> -->

                        <!-- <label class="form-control-label" v-text="$t('riportalApp.company.logo')" for="company-logo">Logo</label>
                        <select class="form-control" id="company-logo" name="logo" v-model="company.logo">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="company.logo && documentOption.id === company.logo.id ? company.logo : documentOption" v-for="documentOption in documents" :key="documentOption.id">{{documentOption.id}}</option>
                        </select> -->

                </div>
            </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.company.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./company-update.component.ts">
</script>
