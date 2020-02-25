<template>
  <div id="detail">
    <a-row>
      <a-col :span="16" :offset="4">
        <div class="title">
          {{ title }}
        </div>
        <div class="appendix">
          <a-icon type="clock-circle" />
          {{ description }}
          <a-icon type="message" />
          {{ commentCount }}
        </div>
        <div class="main">
          {{ content }}
        </div>
        <center>
          <img :src="image" alt="" height="400px" style="margin: 32px" />
        </center>
       
      </a-col>
    </a-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      title: "自信对一个人有多重要？",
      description: "发布时间 2020/02/20 18:30",
      commentCount: 25,
      content:
        "自信是一个人幸福生活的重要元素，有自信的人往往生活的更加愉快，会让我们的生活和工作更加顺利。但自信并非“从天而降”，要建立起自信，就需要我们理解自信是什么？有什么提高我们自信的方法？然后接纳自己的优点和不足，善待自己，爱自己。",
      image:
        "https://ossimg.xinli001.com/20200221/2fe9a2e7e659d487a8aa22b38a709fb8.jpeg?x-oss-process=image/quality,Q_80",
    };
  }
};
</script>

<style scoped></style>
