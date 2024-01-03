<template>
  <div id="app">
    <div class="m-content-display">

      <the-header/>
      <div class="m-router-view">
        <router-view/>
      </div>
      <the-footer/>
    </div>
  </div>
</template>

<script lang="ts">
import {defineComponent} from 'vue';
import TheHeader from '@/components/the-header.vue';
import TheFooter from '@/components/the-footer.vue';

export default defineComponent({
  name: 'app',
  components: {
    TheHeader,
    TheFooter,
  },
});
</script>

<style>
.m-content-display {
  display: flex;
  flex-direction: column;
  min-height: 100%;
}

.m-router-view {
  flex: 1;
}

#app {
  /*其他*/
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
}

</style>
