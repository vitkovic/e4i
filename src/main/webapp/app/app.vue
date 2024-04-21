<template>
  <div id="app">
   
    <div id="app-header" class="fixed">
      <jhi-navbar></jhi-navbar>
    </div>
    <notifications width="300px" classes="vue-notification notification-font" position="top center"/> 
    
    <div class="container-fluid">
		
      <div class="card jh-card">
        <router-view></router-view>
      </div>
      <b-modal id="login-page" hide-footer lazy>
        <span slot="modal-title" id="login-title" v-text="$t('login.title')">Sign in</span>
        <login-form></login-form>
      </b-modal>

      <jhi-footer></jhi-footer>
    </div>
    
  </div>
</template>

<script lang="ts" src="./app.component.ts">

</script>

<style scoped>
.fixed{
	position: fixed;
	z-index: 999;
	background-color: white;
	width: 100%;
	box-shadow: 2px 2px 5px rgba(0,0,0, 0.3);
}

.container-fluid{
	padding-top: 9.3rem;
}
</style>