<template>
<el-container class="home-container">
  <el-header>
    <div>
      <img src="" alt="">
      <span>宠物交流者平台</span>
    </div>
    <el-button type="info" @click="logout">退出</el-button>
  </el-header>
  <el-container>
    <!-- 侧边栏 -->
    <el-aside :width="isCollapse ? '64px' : '200px'">
      <div class="toggle-button" @click="toggleCollapse">|||</div>
      <!-- 侧边栏菜单区域 -->
      <el-menu :default-active="this.$router.history.current.path" router background-color="#333744" text-color="#fff" active-text-color="#409EFF" unique-opened :collapse="isCollapse" :collapse-transition="false">
        <!--一级菜单-->
        <el-submenu :index="item.id+''" v-for="(item,indexs) in menulist" :key="item.id">
          <template slot="title">
            <i :class="iconsObj[indexs]"></i>
            <span>{{item.authName}}</span>
          </template>
          <!-- 二级菜单 -->
          <el-menu-item :index="'/' + subItem.path" v-for="subItem in item.children" :key="subItem.id">
            <template slot="title">
              <!-- 图标 -->
              <i class="el-icon-menu"></i>
              <!-- 文本 -->
              <span>{{subItem.authName}}</span>
            </template>
          </el-menu-item>
        </el-submenu>
      </el-menu>
    </el-aside>
    <el-main>
      <router-view></router-view>
    </el-main>
  </el-container>
</el-container>
</template>

<script>
export default {
  name: 'home',
  data() {
    return {
      menulist: [],
      iconsObj: {
        '0': 'iconfont icon-user',
        '1': 'iconfont icon-tijikongjian',
        '2': 'iconfont icon-shangpin',
        '3': 'iconfont icon-danju',
        '4': 'iconfont icon-baobiao'
      },
      // 是否折叠
      isCollapse: false,
    }
  },
}
</script>

<style lang="scss" scoped>
.home-container {
  height: 100%;
}

.el-header {
  background-color: #373d41;
  display: flex;
  justify-content: space-between;
  padding-left: 0;
  align-items: center;
  color: #fff;
  font-size: 20px;

  >div {
    display: flex;
    align-items: center;

    span {
      margin-left: 15px;
    }
  }
}

.el-aside {
  background-color: #333744;

  .el-menu {
    border-right: none;
  }
}

.el-main {
  background-color: #eaedf1;
}

.iconfont {
  margin-right: 10px;
}

.toggle-button {
  background-color: #4a5064;
  font-size: 10px;
  line-height: 24px;
  color: #fff;
  text-align: center;
  letter-spacing: 0.2em;
  cursor: pointer;
}
</style>
