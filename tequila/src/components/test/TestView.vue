<template>
  <div id="test">
    <a-row>
      <a-col :span="12" :offset="6">
        <div class="title" style="margin: 16px; width: 100%;text-align: center;">心理测试</div>
        <a-list size="large" :dataSource="data" itemLayout="vertical">
          <a-list-item slot="renderItem" slot-scope="item, index">
            <div class="question">{{ index + 1 }}. {{ item.q }}</div>

            <div class="choice">
              <a-radio-group @change="onChange" :v-model="'answer'+item">
                <a-radio :style="radioStyle" :value="index+'A'">{{ item.a }}</a-radio>
                <a-radio :style="radioStyle" :value="index+'B'">{{ item.b }}</a-radio>
                <a-radio :style="radioStyle" :value="index+'C'">{{ item.c }}</a-radio>
                <a-radio :style="radioStyle" :value="index+'D'">{{ item.d }}</a-radio>
              </a-radio-group>
            </div>
          </a-list-item>
        </a-list>
        <center> <a-button type="primary" size="large">提交</a-button></center>
       
      </a-col>
    </a-row>
  </div>
</template>
<script>
const data = [
  {
    q: "我觉得比平常容易紧张或着急",
    a: "没有或很少时间有",
    b: "小部分时间有",
    c: "相当时间有",
    d: "绝大部分时间有"
  },
  {
    q: "我觉得比平常容易紧张或着急",
    a: "没有或很少时间有",
    b: "小部分时间有",
    c: "相当时间有",
    d: "绝大部分时间有"
  },
  {
    q: "我觉得比平常容易紧张或着急",
    a: "没有或很少时间有",
    b: "小部分时间有",
    c: "相当时间有",
    d: "绝大部分时间有"
  },
  {
    q: "我觉得比平常容易紧张或着急",
    a: "没有或很少时间有",
    b: "小部分时间有",
    c: "相当时间有",
    d: "绝大部分时间有"
  },
  {
    q: "我觉得比平常容易紧张或着急",
    a: "没有或很少时间有",
    b: "小部分时间有",
    c: "相当时间有",
    d: "绝大部分时间有"
  },
  {
    q: "我觉得比平常容易紧张或着急",
    a: "没有或很少时间有",
    b: "小部分时间有",
    c: "相当时间有",
    d: "绝大部分时间有"
  },
  {
    q: "我觉得比平常容易紧张或着急",
    a: "没有或很少时间有",
    b: "小部分时间有",
    c: "相当时间有",
    d: "绝大部分时间有"
  },
  {
    q: "我觉得比平常容易紧张或着急",
    a: "没有或很少时间有",
    b: "小部分时间有",
    c: "相当时间有",
    d: "绝大部分时间有"
  },
  {
    q: "我觉得比平常容易紧张或着急",
    a: "没有或很少时间有",
    b: "小部分时间有",
    c: "相当时间有",
    d: "绝大部分时间有"
  },
  {
    q: "我觉得比平常容易紧张或着急",
    a: "没有或很少时间有",
    b: "小部分时间有",
    c: "相当时间有",
    d: "绝大部分时间有"
  }
];

export default {
  data() {
    return {
      data,
      answer0: '',
      answer1: '',
      answer2: '',
      answer3: '',
      answer4: '',
      answer5: '',
      answer6: '',
      answer7: '',
      answer8: '',
      answer9: '',
      radioStyle: {
        display: "block",
        height: "30px",
        lineHeight: "30px"
      }
    };
  },
  methods: {
    onChange(e) {
      console.log("radio checked", e.target.value);
    }
  }
};
</script>
<style>
#test {
  margin: 0 0;
}
.question {
  font-size: 18px;
  color: rgba(0, 0, 0, 0.85);
  margin-bottom: 16px;
}
</style>
