<template>
  <div class="app-container">

    <el-row :gutter="20">
      <!--部门数据-->
      <el-col :span="10" :xs="24" max-height="25">
        <h1 style="color: rgba(0,0,0,0.97);">
          <i class="el-icon-monitor" style="color: #000000;"></i>文件资源管理器</h1>
        <el-tree
          :props="props"
          :load="loadNode"
          lazy
          show-checkbox
          @node-click="handleNodeClick">
          <!--重点：给节点添加图标-->
          <span slot-scope="{ node, data }" class="slot-t-node">
             <template>
               <i
                 :class="{
                   'el-icon-folder': !node.expanded,
                   'el-icon-folder-opened': node.expanded,
                   'el-icon-eleme': data.isDirectory === 0
                 }"
                 style="color: #409eff;"
               />
               <span>{{ node.data.name }}</span>
             </template>
          </span>
        </el-tree>

        <el-dialog
          title="提示"
          :visible.sync="dialogVisible"
          width="30%"
          :before-close="handleClose"
          :closeOnClickModal="false">
          <span>这是个文件</span>
          <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="loadTheFile">确 定</el-button>
          </span>
        </el-dialog>
      </el-col>
      <el-col :span="14" :xs="24">

        <el-table v-loading="loading" :data="this.zijiedian" style="width: 100%" @row-dblclick="haha">
          <el-table-column prop="name" label="文件名" width="300"></el-table-column>
          <el-table-column prop="url" label="文件路径" width="400"></el-table-column>
        </el-table>

        <!--<el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">-->
          <!--<el-form-item label="本级" prop="thisLevelId">-->
            <!--<el-input-->
              <!--v-model="queryParams.thisLevelId"-->
              <!--placeholder="请输入本级"-->
              <!--clearable-->
              <!--@keyup.enter.native="handleQuery"-->
            <!--/>-->
          <!--</el-form-item>-->
          <!--<el-form-item label="名称" prop="name">-->
            <!--<el-input-->
              <!--v-model="queryParams.name"-->
              <!--placeholder="请输入名称"-->
              <!--clearable-->
              <!--@keyup.enter.native="handleQuery"-->
            <!--/>-->
          <!--</el-form-item>-->
          <!--<el-form-item label="上级" prop="superLevel">-->
            <!--<el-input-->
              <!--v-model="queryParams.superLevel"-->
              <!--placeholder="请输入上级"-->
              <!--clearable-->
              <!--@keyup.enter.native="handleQuery"-->
            <!--/>-->
          <!--</el-form-item>-->
          <!--<el-form-item>-->
          <!--<el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>-->
            <!--<el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>-->
          <!--</el-form-item>-->
        <!--</el-form>-->

        <!--<el-row :gutter="10" class="mb8">-->
          <!--<el-col :span="1.5">-->
            <!--<el-button-->
              <!--type="primary"-->
              <!--plain-->
              <!--icon="el-icon-plus"-->
              <!--size="mini"-->
              <!--@click="handleAdd"-->
              <!--v-hasPermi="['ceshi:manager:add']"-->
            <!--&gt;新增</el-button>-->
          <!--</el-col>-->
          <!--<el-col :span="1.5">-->
            <!--<el-button-->
              <!--type="info"-->
              <!--plain-->
              <!--icon="el-icon-sort"-->
              <!--size="mini"-->
              <!--@click="toggleExpandAll"-->
            <!--&gt;展开/折叠</el-button>-->
          <!--</el-col>-->
          <!--<right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>-->
        <!--</el-row>-->

        <!--<el-table-->
          <!--v-if="refreshTable"-->
          <!--v-loading="loading"-->
          <!--:data="managerList"-->
          <!--row-key="thisLevelId"-->
          <!--:default-expand-all="isExpandAll"-->
          <!--:tree-props="{children: 'children', hasChildren: 'hasChildren'}"-->
        <!--&gt;-->
          <!--<el-table-column label="本级" prop="thisLevelId" />-->
          <!--<el-table-column label="名称" align="center" prop="name" />-->
          <!--<el-table-column label="上级" align="center" prop="superLevel" />-->
          <!--<el-table-column label="操作" align="center" class-name="small-padding fixed-width">-->
            <!--<template slot-scope="scope">-->
              <!--<el-button-->
                <!--size="mini"-->
                <!--type="text"-->
                <!--icon="el-icon-edit"-->
                <!--@click="handleUpdate(scope.row)"-->
                <!--v-hasPermi="['ceshi:manager:edit']"-->
              <!--&gt;修改</el-button>-->
              <!--<el-button-->
                <!--size="mini"-->
                <!--type="text"-->
                <!--icon="el-icon-plus"-->
                <!--@click="handleAdd(scope.row)"-->
                <!--v-hasPermi="['ceshi:manager:add']"-->
              <!--&gt;新增</el-button>-->
              <!--<el-button-->
                <!--size="mini"-->
                <!--type="text"-->
                <!--icon="el-icon-delete"-->
                <!--@click="handleDelete(scope.row)"-->
                <!--v-hasPermi="['ceshi:manager:remove']"-->
              <!--&gt;删除</el-button>-->
            <!--</template>-->
          <!--</el-table-column>-->
        <!--</el-table>-->

        <!--&lt;!&ndash; 添加或修改测试对话框 &ndash;&gt;-->
        <!--<el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>-->
          <!--<el-form ref="form" :model="form" :rules="rules" label-width="80px">-->
            <!--<el-form-item label="本级" prop="thisLevelId">-->
              <!--<el-input v-model="form.thisLevelId" placeholder="请输入本级" />-->
            <!--</el-form-item>-->
            <!--<el-form-item label="名称" prop="name">-->
              <!--<el-input v-model="form.name" placeholder="请输入名称" />-->
            <!--</el-form-item>-->
            <!--<el-form-item label="上级" prop="superLevel">-->
              <!--<treeselect v-model="form.superLevel" :options="managerOptions" :normalizer="normalizer" placeholder="请选择上级" />-->
            <!--</el-form-item>-->
          <!--</el-form>-->
          <!--<div slot="footer" class="dialog-footer">-->
            <!--<el-button type="primary" @click="submitForm">确 定</el-button>-->
            <!--<el-button @click="cancel">取 消</el-button>-->
          <!--</div>-->
        <!--</el-dialog>-->
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { loadTheFileManager,nodeManager,listManager, getManager, delManager, addManager, updateManager } from "@/api/ceshi/manager";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import axios from 'axios';

export default {
  name: "Manager",
  components: {
    Treeselect
  },
  data() {
    return {
      //
      haha(){
        alert("你好...");
      },
      zijiedian: null,
      display: null,
      linshi:null,
      //控制对话框开关
      dialogVisible: false,
      //节点数据
      data:[],
      //初始化数据
      top:[{  name: "C:",
              url: "C:\\",
              isLeaf: false,
              isDirectory:1,
              parentUrl:null
              }],
      //tree初始化
      props: {
        label: 'name',
        children: 'zones',
        isLeaf: 'isLeaf'
      },
      //

      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 测试表格数据
      managerList: [],
      // 测试树选项
      managerOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否展开，默认全部展开
      isExpandAll: true,
      // 重新渲染表格状态
      refreshTable: true,
      // 查询参数
      queryParams: {
        thisLevelId: null,
        name: null,
        superLevel: null,
        url: null,
        fileName:null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        thisLevelId: [
          { required: true, message: "本级不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    //
    //加载文件
    loadTheFile(){
      this.dialogVisible = false;
      this.queryParams.fileName = this.linshi.name;
      this.queryParams.url = this.linshi.url;
      loadTheFileManager(this.queryParams).then(response => {
        this.display = response.data;
      });
      if(this.display != true) return;
      this.downloadFile();
    },
    downloadFile() {
        // 发送HTTP GET请求获取文件数据
        axios.get('profile/upload/file/' + this.linshi.name, {
          responseType: 'blob', // 指定响应类型为二进制数据
        })
        .then(response => {
          // 创建一个Blob对象，表示文件数据
          const blob = new Blob([response.data], { type: response.headers['content-type'] });

          // 使用URL.createObjectURL创建文件的URL
          const url = window.URL.createObjectURL(blob);

          // 创建一个<a>元素用于下载
          const a = document.createElement('a');
          a.href = url;
          a.download = 'file-name.ext'; // 指定文件名
          a.style.display = 'none';

          // 将<a>元素添加到DOM并触发点击事件来下载文件
          document.body.appendChild(a);
          a.click();

          // 清理URL对象，释放资源
          window.URL.revokeObjectURL(url);
        })
        .catch(error => {
          console.error('下载文件时发生错误：', error);
        });
    },
    /** 关闭对话框 */
    handleClose(done) {
      this.dialogVisible = false;
      // this.$confirm('确认关闭？')
      //   .then(_ => {
      //     done();
      //   })
      //   .catch(_ => {});
    },
    /** 节点单击事件 */
    handleNodeClick(data) {
      if(data.isLeaf == false) return;
      this.linshi = data;
      this.dialogVisible = true;
    },
    /** 懒加载 */
    loadNode(node, resolve) {
      if (node.level === 0) {
        return resolve(this.top);
      }
      if(node.data.isDirectory == 0)
        return;
      else
        this.getNode(node);
      setTimeout(() => {
        resolve(this.data);
      }, 500);
    },
    /** 获取子节点数据 */
    getNode(node) {
      this.queryParams.url = node.data.url;
      nodeManager(this.queryParams).then(response => {
        this.data = this.handleTree(response.data, "url", "parentUrl");
        this.zijiedian = this.data;
        for(var i in this.data){
          if(this.data[i].isDirectory === 1)
            this.data[i].isLeaf = false;
          else
            this.data[i].isLeaf = true;
        }
      });
    },
    //


    /** 查询测试列表 */
    getList() {
      this.loading = true;
      listManager(this.queryParams).then(response => {
        this.managerList = this.handleTree(response.data, "thisLevelId", "superLevel");
        this.loading = false;
      });
    },
    /** 转换测试数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.thisLevelId,
        label: node.name,
        children: node.children
      };
    },
	/** 查询测试下拉树结构 */
    getTreeselect() {
      listManager().then(response => {
        this.managerOptions = [];
        const data = { thisLevelId: 0, name: '顶级节点', children: [] };
        data.children = this.handleTree(response.data, "thisLevelId", "superLevel");
        this.managerOptions.push(data);
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        thisLevelId: null,
        name: null,
        superLevel: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd(row) {
      this.reset();
      this.getTreeselect();
      if (row != null && row.thisLevelId) {
        this.form.superLevel = row.thisLevelId;
      } else {
        this.form.superLevel = 0;
      }
      this.open = true;
      this.title = "添加测试";
    },
    /** 展开/折叠操作 */
    toggleExpandAll() {
      this.refreshTable = false;
      this.isExpandAll = !this.isExpandAll;
      this.$nextTick(() => {
        this.refreshTable = true;
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.getTreeselect();
      if (row != null) {
        this.form.superLevel = row.superLevel;
      }
      getManager(row.id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改测试";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateManager(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addManager(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      this.$modal.confirm('是否确认删除测试编号为"' + row.id + '"的数据项？').then(function() {
        return delManager(row.id);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    }
  }
};
</script>
