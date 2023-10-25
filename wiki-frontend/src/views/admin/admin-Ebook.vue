<template>

  <a-layout>
    <a-layout-content :style="{ padding: '0 24px', minHeight: '280px' }">
      <a-table :columns="columns" :data-source="ebook" :row-key="record => record.id" :pagination="pagination"
               :loading="loading"
               @change="handleTableChange">
        <template #bodyCell="{ column, record }">
          <template v-if="column.key === 'cover'">
            <a-space wrap :size="5">
              <a-avatar shape="square" :size="64" :src="record.cover">
              </a-avatar>
            </a-space>
          </template>

          <template v-if="column.key === 'name'">
            <a>
              {{ record.name }}
            </a>
          </template>

          <template v-if="column.key === 'category1Id'">
            <a>
              {{ record.category1Id }}
            </a>
          </template>

          <template v-if="column.key === 'category2Id'">
            <a>
              {{ record.category2Id }}
            </a>
          </template>
          <template v-if="column.key === 'docCount'">
            <a>
              {{ record.docCount }}
            </a>
          </template>

          <template v-if="column.key === 'viewCount'">
            <a>
              {{ record.viewCount }}
            </a>
          </template>

          <template v-if="column.key === 'voteCount'">
            <a>
              {{ record.voteCount }}
            </a>
          </template>

          <template v-if="column.key === 'action'">
            <a-space wrap>
              <a-button type="primary" @click="edit(record)">编辑</a-button>
              <a-popconfirm
                  title="删除后不可恢复，确认删除?"
                  ok-text="是"
                  cancel-text="否"
                  @confirm="handleDelete(record.id)"
              >
                <a-button type="primary" danger>删除</a-button>
              </a-popconfirm>
            </a-space>
          </template>

        </template>
      </a-table>
    </a-layout-content>
  </a-layout>
</template>

<script setup lang="ts">
import {onMounted, ref} from "vue";
import axios from "axios";
import {message} from "ant-design-vue";

const param = ref();
param.value = {};
const loading = ref(false);
const ebook = ref();
const modalVisible = ref(false);
const pagination = ref({
  current: 1,
  pageSize: 2,
  total: 0
});
const data = ref();

onMounted(() => {
  handleQuery({});
});

const columns = [
  {
    title: '封面',
    dataIndex: 'cover',
    key: 'cover',
  },
  {
    title: '名称',
    dataIndex: 'name',
    key: 'name',
  },
  {
    title: '分类一',
    dataIndex: 'category1Id',
    key: 'category1Id',
  },
  {
    title: '分类二',
    key: 'category2Id',
    dataIndex: 'category2Id',
  },
  {
    title: '文档数',
    key: 'docCount',
    dataIndex: 'docCount',
  },
  {
    title: '阅读数',
    key: 'viewCount',
    dataIndex: 'viewCount',
  },
  {
    title: '点赞数',
    key: 'voteCount',
    dataIndex: 'voteCount',
  },
  {
    title: 'Action',
    key: 'action',
  },
];

/**
 * 表格点击页码时触发
 */
const handleTableChange = (pagination: any) => {
  console.log("看看自带的分页参数都有啥：" + pagination);
  handleQuery({
    page: pagination.current,
    size: pagination.pageSize
  });
};

/**
 * 数据查询
 **/
const handleQuery = (params: any) => {
  loading.value = true;
  // 如果不清空现有数据，则编辑保存重新加载数据后，再点编辑，则列表显示的还是编辑前的数据
  ebook.value = [];
  axios.get("/ebook/list", params).then((response) => {
    loading.value = false;
    /*const data = response.data;
    if (data.data.code === 1) {
      ebook.value = data.data;

      // 重置分页按钮
      pagination.value.current = params.page;
      pagination.value.total = data.content.total;
    } else {
      message.error(data.data.msg);
    }*/
    const data = response.data;
    ebook.value = data.data;
    console.log(ebook.value);
    pagination.value.current = params.page;
  });
};

const handleDelete = (id: number) => {
  axios.delete("/ebook/delete/" + id).then((response) => {
    const data = response.data; // data = commonResp
    if (data.data.code === 1) {
      // 重新加载列表
      handleQuery({
        page: pagination.value.current,
        size: pagination.value.pageSize,
      });
    } else {
      message.error(data.message);
    }
  });
};

/**
 * 编辑
 */
const edit = (record: any) => {
  /*modalVisible.value = true;
  ebook.value = Tool.copy(record);
  categoryIds.value = [ebook.value.category1Id, ebook.value.category2Id]*/
};

/**
 * 数据查询
 **/
// const handleQuery = (params: any) => {
//   loading.value = true;
//   // 如果不清空现有数据，则编辑保存重新加载数据后，再点编辑，则列表显示的还是编辑前的数据
//   ebook.value = [];
//   axios.get("/ebook/list", {
//     params: {
//       page: params.page,
//       size: params.size,
//       name: param.value.name
//     }
//   }).then((response) => {
//     loading.value = false;
//     const data = response.data;
//     if (data.data.code === 1) {
//       ebook.value = data.data.list;
//
//       // 重置分页按钮
//       pagination.value.current = params.page;
//       pagination.value.total = data.content.total;
//     } else {
//       message.error(data.message);
//     }
//   });
// };

</script>