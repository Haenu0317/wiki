<template>
  <a-layout-header class="header">
    <div class="logo">挂件知识库</div>
    <a-menu
      theme="dark"
      mode="horizontal"
      :style="{ lineHeight: '64px' }"
    >
      <a-menu-item key="/">
        <router-link to="/">首页</router-link>
      </a-menu-item>
      <a-menu-item key="/admin/user" :style="user.loginName==='都是挂件'? {} : {display:'none'}">
        <router-link to="/admin/user">用户管理</router-link>
      </a-menu-item>
      <a-menu-item key="/admin/ebook" :style="user.loginName==='都是挂件'? {} : {display:'none'}">
        <router-link to="/admin/ebook">电子书管理</router-link>
      </a-menu-item>
      <a-menu-item key="/admin/category" :style="user.loginName==='都是挂件'? {} : {display:'none'}">
        <router-link to="/admin/category">分类管理</router-link>
      </a-menu-item>

      <a-popconfirm
        title="确认退出登录?"
        ok-text="是"
        cancel-text="否"
        @confirm="logout()"
      >
        <a class="login-menu" v-show="user.id">
          <span>退出登录</span>
        </a>
      </a-popconfirm>
      <a class="login-menu" v-show="user.id">
        <span>您好：{{user.name}}</span>
      </a>
      <a class="register-menu" v-show="!user.id" @click="add()">
        <span>注册</span>
      </a>
      <a class="login-menu" v-show="!user.id" @click="showLoginModal">
        <span>登录</span>
      </a>
    </a-menu>

    <a-modal
      title="登录"
      v-model:visible="loginModalVisible"
      :confirm-loading="loginModalLoading"
      @ok="login"
    >
      <a-form :model="loginUser" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
        <a-form-item label="登录名">
          <a-input v-model:value="loginUser.loginName" />
        </a-form-item>
        <a-form-item label="密码">
          <a-input v-model:value="loginUser.password" type="password" />
        </a-form-item>
      </a-form>
    </a-modal>

    <a-modal
        title="注册"
        v-model:visible="modalVisible"
        :confirm-loading="modalLoading"
        @ok="handleModalOk"
    >
      <a-form :model="registerUser" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
        <a-form-item label="登陆名">
          <a-input v-model:value="user.loginName" :disabled="!!user.id"/>
        </a-form-item>
        <a-form-item label="昵称">
          <a-input v-model:value="user.name" />
        </a-form-item>
        <a-form-item label="密码" v-show="!user.id">
          <a-input v-model:value="user.password" type="password"/>
        </a-form-item>
      </a-form>
    </a-modal>
  </a-layout-header>
</template>

<script lang="ts">
  import { defineComponent, ref, computed } from 'vue';
  import axios from 'axios';
  import { message } from 'ant-design-vue';
  import store from "@/store";

  declare let hexMd5: any;
  declare let KEY: any;

  export default defineComponent({
    name: 'the-header',
    setup () {
      // 登录后保存
      const user = computed(() => store.state.user);

      // 用来登录
      const loginUser = ref({
        loginName: "都是挂件",
        password: "test123"
      });
      const registerUser = ref({
        loginName: "",
        name: "",
        password: ""
      });
      const loginModalVisible = ref(false);
      const loginModalLoading = ref(false);
      const modalVisible = ref(false);
      const modalLoading = ref(false);

      const showLoginModal = () => {
        loginModalVisible.value = true;
      };



      /**
       * 新增
       */
      const add = () => {
        modalVisible.value = true;
        registerUser.value = {
          loginName: "",
          name: "",
          password: ""
        }
      };
      // 登录
      const login = () => {
        console.log("开始登录");
        loginModalLoading.value = true;
        //loginUser.value.password = hexMd5(loginUser.value.password + KEY);
        console.log('------------' + loginUser.value.password)
        axios.post('/user/login', loginUser.value).then((response) => {
          loginModalLoading.value = false;
          const data = response.data;
          if (data.success) {
            loginModalVisible.value = false;
            message.success("登录成功！");

            store.commit("setUser", data.content);
          } else {
            message.error(data.message);
          }
        });
      };

      // 退出登录
      const logout = () => {
        console.log("退出登录开始");
        axios.get('/user/logout/' + user.value.token).then((response) => {
          const data = response.data;
          if (data.success) {
            message.success("退出登录成功！");
            store.commit("setUser", {});
          } else {
            message.error(data.message);
          }
        });
      };

      const handleModalOk = () => {
        modalLoading.value = true;
        axios.post("/user/save", user.value).then((response) => {
          modalLoading.value = false;
          const data = response.data; // data = commonResp
          if (data.success) {
            modalVisible.value = false;
            message.success('注册成功!')
          } else {
            message.error(data.message);
          }
        });
      };

      return {
        registerUser,
        handleModalOk,
        modalLoading,
        add,
        modalVisible,
        loginModalVisible,
        loginModalLoading,
        showLoginModal,
        loginUser,
        login,
        user,
        logout
      }
    }
  });
</script>

<style>
  .logo {
    width: 120px;
    height: 40px;
    //background: rgba(255, 255, 255, 0.2);
    /*margin: 16px 28px 16px 0;*/
    float: left;
    color: white;
    font-size: 18px;
  }
  .login-menu {
    float: right;
    color: white;
    padding-left: 20px;
  }
  .register-menu {
    float: right;
    color: white;
    padding-left: 40px;
  }
</style>
