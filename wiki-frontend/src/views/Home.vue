<template>
  <a-layout>
    <a-layout-sider width="200" style="background: #fff">
      <a-menu
          v-model:selectedKeys="selectedKeys2"
          v-model:openKeys="openKeys"
          mode="inline"
          style="height: 100%"
      >
        <a-sub-menu key="sub1">
          <template #title>
                <span>
                  <user-outlined/>
                  subnav 1
                </span>
          </template>
          <a-menu-item key="1">option1</a-menu-item>
          <a-menu-item key="2">option2</a-menu-item>
          <a-menu-item key="3">option3</a-menu-item>
          <a-menu-item key="4">option4</a-menu-item>
        </a-sub-menu>
        <a-sub-menu key="sub2">
          <template #title>
                <span>
                  <laptop-outlined/>
                  subnav 2
                </span>
          </template>
          <a-menu-item key="5">option5</a-menu-item>
          <a-menu-item key="6">option6</a-menu-item>
          <a-menu-item key="7">option7</a-menu-item>
          <a-menu-item key="8">option8</a-menu-item>
        </a-sub-menu>
        <a-sub-menu key="sub3">
          <template #title>
                <span>
                  <notification-outlined/>
                  subnav 3
                </span>
          </template>
          <a-menu-item key="9">option9</a-menu-item>
          <a-menu-item key="10">option10</a-menu-item>
          <a-menu-item key="11">option11</a-menu-item>
          <a-menu-item key="12">option12</a-menu-item>
        </a-sub-menu>
      </a-menu>
    </a-layout-sider>
    <a-layout-content :style="{ padding: '0 24px', minHeight: '280px' }">
      <pre>
{{ebooks1}}
      </pre>
    </a-layout-content>
  </a-layout>
</template>


<script lang="ts" setup>
import {ref, defineComponent, onMounted, reactive, toRef} from 'vue';
import {UserOutlined, LaptopOutlined, NotificationOutlined} from '@ant-design/icons-vue';
import axios from 'axios';

const selectedKeys1 = ref<string[]>(['2']);
const selectedKeys2 = ref<string[]>(['1']);
const openKeys = ref<string[]>(['sub1']);
const ebooks = ref();
const ebooks1 = reactive({
  books: []
});

onMounted(() => {
  console.log("onMounted")
  axios.get("http://localhost:8081/user/list").then((response) => {
    ebooks.value = response.data.data;
    ebooks1.books = response.data.data;
    console.log(ebooks.value);
  })
})

</script>

