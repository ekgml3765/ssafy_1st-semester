use workshop;

select * from emp;

# 1) 입사일이 1981년도인 사람의 모든 정보를 검색하세요.
select *
from emp
where year(hiredate) = 1981;

# 2) 이름 중 S자가 들어가 있는 사람만 모든 정보를 검색하세요.
select *
from emp
where ename like '%S%';

# 3) 커미션이 null인 사람의 정보를 검색하세요.
select *
from emp
where comm is null;

# 4) 30번 부서의 연봉을 계산하여 이름, 부서번호, 급여, 연봉(12개월 월급여 + 연말보너스)을 검색하세요. 단 연말의 150%를 급여의 보너스
select ename, deptno, sal, truncate((12*sal) + (sal*1.5),0) "연봉"
from emp;

# 5) 급여가 $2000 이상이네 모든 사람은 급여의 15%를 경조비로 내기로 하였다. 이름. 급여 경조비 검색
select ename, sal, truncate(sal*0.15,0) "경조비"
from emp
where sal > 2000;