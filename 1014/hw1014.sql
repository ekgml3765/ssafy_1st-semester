use ssafydb;

# 1) 'Diana' 사원과 같은 직무인 사원의 사번, 이름, 직무, 부서번호, 부서이름 출력하세요
select employee_id 사번, first_name 이름, job_title 직무, e.department_id 부서번호, department_name 부서이름
from employees e join jobs using(job_id)
join departments using(department_id)
where job_id = (select job_id
from employees
where first_name = 'Diana');

# 2)'bruce'와 같은 매니저인 사원의 정보를 inline view를 사용하여 아래의 결과와 같이 출력하세요.
# 사번, 이름, 직무이름, 부서이름
select employee_id, first_name, job_title, department_name
from employees ee join jobs using(job_id)
join departments using(department_id)
where ee.manager_id = (select e.manager_id
from (select * from employees) e join employees m on e.manager_id = m. employee_id
where e.first_name = 'bruce');

#3) 모든 사원을 입사일 순으로 정렬하고 가장 오래된 순으로 6번째부터 10번쨰까지 사원의 정보를 추력하시오.
select employee_id, first_name, hire_date
from employees
order by hire_date asc limit 5, 5;