<template>
  <div>
    <div>
        <label for="9g3er">文章标题</label>
        <a-input id="9g3er" placeholder="输入文章标题" style="width: 400px"/>
        <label for="9g3er">文章封面图片</label>
        <a-input id="9g3er" placeholder="图片网络地址 http://... 或者 https://..." style="width: 400px"/>
    </div>
    <div id="editor"></div>
    <a-button type="primary" @click="show" style="margin-top: 16px">发布</a-button>
  </div>
</template>

<script>
import E from "wangeditor";

export default {
  name: "editor",
  data() {
    return {
      editorContent: ""
    };
  },
  mounted() {
    var editor = new E("#editor");
    editor.customConfig.onchange = html => {
      this.editorContent = html;
    };
    editor.create();
  },
  methods: {
    show() {
      console.log(this.editorContent);
    }
  }
};
</script>

<style scoped>
label {
    
    display: block;
    margin: 8px 0;
}

#editor {
    margin-top: 16px;
}
</style>