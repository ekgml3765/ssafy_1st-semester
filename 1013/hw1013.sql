use workshop;

# 1) 30번 부서에 속한 사원의 이름, 부서번호, 부서이름 출력
select ename, d.deptno, dname
from emp e join dept d using (deptno)
where d.deptno = 30;


# 2) 30번 부서에 속한 사원들의 모든 직업과 부서 위치를 출력하라.
# 단, 직업 목록이 중복되지 않게 하라.
select distinct job, loc, d.deptno
from emp e join dept d using (deptno)
where d.deptno = 30;

# 3) 커미션이 책정되어있는 모든 사원의 이름, 부서이름, 및 위치를 출력하라
select  ename, dname, loc, comm
from emp e join dept d using (deptno)
where comm is not null;

# 4) 이름에 A가 들어가는 모든 사원의 이름과 부서이름을 출력하라.
select ename, dname
from emp e join dept d using (deptno)
where ename like '%a%';

#5) DAllas에서 근무하는 모든 사원의 이름, 직업, 부서번호 및 부서이름 출력하라
select ename, job, d.DEPTNO, d.DNAME
from emp e join dept d using (deptno)
where d.loc = 'dallas';

#6) 사원이름, 사원번호, 해당 관리자 이름 및 관리자번호 출력하되,
#각 컬럼명을 employee, emp#, manager, mgr#으로 출력하라

select e.ename 'employee',  e.empno 'emp#', m.ename 'manager', m.empno 'mgr#'
from emp e left join emp m on(e.mgr = m.empno);

# 7) 모든 사원의 이름, 직업, 부서이름, 급여 및 등급을 출력하라.
 select e.ename, e.job, d.dname, e.sal, grade
 from emp e join dept d using (deptno)
 join salgrade s on (e.sal between s.losal and s.hisal)
 where e.sal;
 
 # 8) smith보다 늦게 입사한 사원의 이름 및 입사일 출력하라.
 select ename, hiredate
 from emp
 where hiredate > (select hiredate
 from emp
 where ename = 'smith'
);

# 9) 자신의 관리자보다 먼저 입사한 모든 사원의 이름, 입사일, 관리자의 이름, 관리자의 입사일 출력
select e.ENAME employee, e.HIREDATE EmpHiredate, m.ename Manager, m.HIREDATE MgrHiredate
from emp e left join emp m on(e.mgr = m.empno)
where e.HIREDATE < m.HIREDATE;

# 10) smith와 동일한 부서에 속한 모든 사원의 이름 및 입사일. 단 smith 제외
select ename, hiredate, deptno
from emp
where ename != 'smith' and deptno = (select deptno
from emp
where ename = 'smith');

# 11) 자신의 급여가 평균 급여보다 많은 모든 사원의 사원번호, 이름, 급여 출력. 급여 기준 결과 내림차순
select EMPNO, ename, sal
from emp
where sal > (
select avg(sal)
from emp
)
order by sal desc;

# 12) 이름에 T가 들어가는 사원의 속한 부서에서 근무하는 모든 사원의 사원번호 및 이름 출력
select ENAME, EMPNO, sal
from emp
where deptno in (
select deptno
from emp
where ename like '%T%');

# 13) 자신의 급여가 평균 급여보다 많고, 이름에 T가 들어가는 사원과 동일한 부서에서 근무하는
# 모든 사원의 사원번호, 이름, 급여 출력 

select EMPNO, ename, sal
from emp
where sal > ( select avg(sal) from emp) 
and deptno in (select deptno from emp where ename like '%T%');

# 14) 직업이 Clerk인 사원들 보다 더 많은 급여를 받는 사원의 사원번호, 이름, 급여 출력
# 결과를 급여가 높은순으로 정렬
select  empno, ename, sal
from emp
where sal  > all (select sal
from emp
where job = 'clerk')
order by sal desc;

#15 New york에서 근무하는 사원과 급여 및 커미션이 같은 사원의 사원이름과 부서명
select ename, dname , sal, comm 
from emp join dept
where sal = (select sal
from emp e join dept d using(deptno)
where loc = 'new york' and comm is not null)
and comm = (select comm
from emp e join dept d using(deptno)
where loc = 'new york' and comm is not null);

select sal, comm
from emp e join dept d using(deptno)
where loc = 'new york' and comm is not null



