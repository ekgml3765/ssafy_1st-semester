export default {
  template: `
    <div id="workshop">
    <h1 style="text-align: center">Vue를 이용한 게시판</h1>
    <table class="table table-bordered">
      <thead>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>날짜</th>
      </thead>
      <tbody>
        <tr v-for="board in boards">
          <td>{{board.no}}</td>
          <td><router-link :to="{name:'Read', params:{no:board.no}}">{{board.title}}</router-link></td>
          <td>{{board.writer}}</td>
          <td>{{board.regtime}}</td>
        </tr>
    </table>
    <button @click="click">등록</button>
  </div>
    `,
  data() {
    return {
      boards: getboards(),
      url: getUrl(),
    };
  },
  methods: {
    click() {
      this.$router.push({ name: 'regist' });
    },
  },
};

function getboards() {
  let boards = JSON.parse(localStorage.getItem('boards'));
  if (boards == undefined) {
    boards = [];
    localStorage.setItem('boards', JSON.stringify(boards));
  }
  return boards;
}

function getUrl() {
  return '';
}
