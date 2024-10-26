from faker import Faker
import random
from datetime import datetime, timedelta
import mysql.connector

fake = Faker('ko_KR')

# MySQL 연결 설정
db_config = {
    'host': 'localhost',
    'user': 'jb',
    'password': 'jb',
    'database': 'jb'
}

department_code = ['MGT', 'NEW', 'FIN', 'SUP', 'LEG', 'HR', 
                   'IT', 'AI', 'BIZ', 'SAL', 'CS',
                   'SAF', 'FAC', 'CON', 'PWR']
department_name = ["경영기획", "신규사업", "재무", "경영지원", "법무", "HR",
                   "IT", "인공지능-빅데이터", "사업개발/지원", "영업", "CS",
                   "안전기획", "시설관리", "공사관리", "발전 설비"]

position_code = ["J1", "J2", "J3", "J4_1", "J4_2"]
position_name = ["주임", "대리", "과장", "팀장", "임원"]

def generate_departments():
    return list(zip(department_code, department_name))

def generate_positions():
    return list(zip(position_code, position_name))

def generate_employees(num=50):
    employees = []
    for _ in range(num):
        hire_date = fake.date_between(start_date='-10y', end_date='today')
        employee = {
            'employee_name': fake.name(),
            'gender': random.choice(['M', 'F']),
            'department_code': fake.random_int(min=1, max=15),
            'position_code': fake.random_int(min=1, max=5),
            'salary': fake.random_int(min=30000000, max=120000000),
            'hire_date': hire_date.strftime('%Y-%m-%d')
        }
        employees.append(employee)
    return employees



def insert_departments_to_mysql(departments):
    try:
        conn = mysql.connector.connect(**db_config)
        cursor = conn.cursor()

        # 부서 데이터 삽입
        dept_insert_query = """
        INSERT INTO department (department_code, department_name)
        VALUES (%s, %s)
        """
        cursor.executemany(dept_insert_query, departments)

        conn.commit()
        print("부서 데이터 삽입 완료")

    except mysql.connector.Error as error:
        print(f"부서 데이터 삽입 실패: {error}")

    finally:
        if conn.is_connected():
            cursor.close()
            conn.close()


def insert_positions_to_mysql(positions):
    try:
        conn = mysql.connector.connect(**db_config)
        cursor = conn.cursor()

        # 직급 데이터 삽입
        pos_insert_query = """
        INSERT INTO position (position_code, position_name)
        VALUES (%s, %s)
        """
        cursor.executemany(pos_insert_query, positions)

        conn.commit()
        print("직급 데이터 삽입 완료")

    except mysql.connector.Error as error:
        print(f"직급 데이터 삽입 실패: {error}")

    finally:
        if conn.is_connected():
            cursor.close()
            conn.close()


def insert_employees_to_mysql(employees):
    try:
        conn = mysql.connector.connect(**db_config)
        cursor = conn.cursor()

        # 직원 데이터 삽입
        emp_insert_query = """
        INSERT INTO employee (name, gender, department_id, position_id, salary, hire_date)
        VALUES (%s, %s, %s, %s, %s, %s)
        """
        cursor.executemany(emp_insert_query, [
            (e['employee_name'], e['gender'], e['department_code'], e['position_code'], e['salary'], e['hire_date'])
            for e in employees
        ])

        conn.commit()
        print("직원 데이터 삽입 완료")

    except mysql.connector.Error as error:
        print(f"직원 데이터 삽입 실패: {error}")

    finally:
        if conn.is_connected():
            cursor.close()
            conn.close()



# 데이터 생성
staff_departments = generate_departments()
staff_positions = generate_positions()
staff_employees = generate_employees(num=1000)  # 100명의 직원 생성

# MySQL에 데이터 삽입
insert_departments_to_mysql(staff_departments)
insert_positions_to_mysql(staff_positions)
insert_employees_to_mysql(staff_employees)



# 생성된 데이터 출력 (선택사항)
print("\nDepartments:")
for dept in staff_departments:
    print(dept)

print("\nPositions:")
for pos in staff_positions:
    print(pos)

print("\nEmployees (first 5):")
for emp in staff_employees[:5]:
    print(emp)