export default {
  template: `
    <div id="vue">
    <h1 style="text-align: center">Vue를 이용한 게시판</h1>
    <table class="table table-striped">
      <thead>
        <th>번호</th>
        <th>글쓴이</th>
        <th>제목</th>
        <th>날짜</th>
        <th>내용</th>
      </thead>
      <tbody>
        <tr v-for="board in boards" v-if="board.no==no">
          <td>{{board.no}}</td>
          <td>{{board.writer}}</td>
          <td>{{board.title}}</td>
          <td>{{board.regtime}}</td>
          <td>{{board.content}}</td>
        </tr>
      </tbody>
    </table>
    <div style="text-align: right">
      <button type="button" class="btn btn-outline-primary" @click="click">목록</button>
      <button type="button" class="btn btn-outline-primary" @click="del">삭제</button>
    </div>
  </div>
    `,
  data() {
    return {
      boards: boards,
      no: 0,
    };
  },
  methods: {
    click() {
      this.$router.push({ name: 'list' });
    },
    del() {
      for (let i = 0; i < boards.length; ++i) {
        if (boards[i].no == this.no) {
          boards.splice(i, 1);
          break;
        }
      }
      localStorage.setItem('boards', JSON.stringify(boards));
      alert('삭제되었습니다.');
    },
  },
  created() {
    console.dir(this.$route); // 현재 호출된 해당 라우터 정보
    this.no = this.$route.params.no;
  },
};

const boards = JSON.parse(localStorage.getItem('boards'));
