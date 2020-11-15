export default {
  template: `
    
    <div id="main">
    <h1 style="text-align: center">Vue를 이용한 게시판</h1>
      <p>번호</p>
      <input v-model="no" type="text" placeholder="번호를 입력하세요" />
  
      <p>작성자</p>
      <input v-model="writer" type="text" placeholder="작성자를 입력하세요" />
  
      <p>제목</p>
      <input v-model="title" type="text" placeholder="제목을 입력하세요" />
  
      <p>내용</p>
      <input v-model="content" type="text" placeholder="내용을 입력하세요" />
      <div style="text-align: right">
        <button @click="register">등록</button>
        <button @click="list">목록</button>
      </div>
    </div>
    `,
  data() {
    return {
      no: '',
      writer: '',
      title: '',
      content: '',
      regtime: getTime(),
    };
  },
  methods: {
    list() {
      location.href = '#/';
    },
    register() {
      boards.push({
        regtime: this.regtime,
        title: this.title,
        writer: this.writer,
        content: this.content,
        no: this.no,
      });
      localStorage.setItem('boards', JSON.stringify(boards));
      this.no = '';
      this.title = '';
      this.content = '';
      this.writer = '';
      alert('등록되었습니다.');
    },
  },
};

let boards = JSON.parse(localStorage.getItem('boards'));

function getTime() {
  let today = new Date();
  return today.toLocaleDateString();
}
