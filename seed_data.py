import pg8000.native
import ssl

ssl_context = ssl.create_default_context()
con = pg8000.native.Connection(
    'college_erp_user',
    host='dpg-dan65vajnfac73fguijg-a.oregon-postgres.render.com',
    port=5432,
    database='college_erp_zuln',
    password='QLfedoTOKJULEwxlYLepvYGs37IRiD28',
    ssl_context=ssl_context
)

print("Connected to PostgreSQL successfully.")

# 1. Insert Courses
courses_data = [
    (1, 'CS301', 'Algorithm design Analysis', 4, '3', 'CSE', 'Its based on design analysis', 'dr gayatri'),
    (2, 'CS302', 'Data Structures', 3, '2', 'ECE', 'Its a future usable subject', 'Dr. Veer'),
    (3, 'IS306', 'BDMS', 4, '3', 'ISE', 'Hi its a imp subject used for database', 'Dr. Habib'),
    (4, 'IS304', 'Operating System', 3, '3', 'CSE', 'Its also a database subject', 'Dr shukala')
]

for c in courses_data:
    con.run(
        "INSERT INTO courses (id, course_code, course_name, credits, semester, department, description, faculty_name) "
        "VALUES (:id, :code, :name, :credits, :sem, :dept, :desc, :faculty) "
        "ON CONFLICT (id) DO NOTHING",
        id=c[0], code=c[1], name=c[2], credits=c[3], sem=c[4], dept=c[5], desc=c[6], faculty=c[7]
    )
print("Courses inserted.")

# 2. Insert Faculty
faculty_data = [
    (1, 'ME', 'HOD Dept of ME', 'sujay456@gmail.com', 'Sujay', 'Male', 'DS', '7512486321'),
    (2, 'EEE', 'Assistant professor', 'umamanu@gmail.com', 'Uma', 'Female', 'manu', '4568712365'),
    (3, 'ME', 'HOD Dept of ME', 'manta23@gmail.com', 'Mahantesh', 'Male', 'VR', '4528796541'),
    (4, 'CSE', 'Assistant professor', 'shivu45@gmail.com', 'Shivu', 'Male', 'kumar', '4562136524')
]

for f in faculty_data:
    con.run(
        "INSERT INTO faculty (id, department, designation, email, first_name, gender, last_name, phone) "
        "VALUES (:id, :dept, :desig, :email, :fname, :gender, :lname, :phone) "
        "ON CONFLICT (id) DO NOTHING",
        id=f[0], dept=f[1], desig=f[2], email=f[3], fname=f[4], gender=f[5], lname=f[6], phone=f[7]
    )
print("Faculty inserted.")

# 3. Insert Students
students_data = [
    (1, 'ISE', 'sheikhshoiebsa17@gmail.com', 'sheikhshoieb', 'Male', 'Ahamad', '7676123458', '6', 'Nayakanahatty , Challakere'),
    (3, 'CSE', 'ayan123@gmail.com', 'ayan', 'Male', 'ssr', '5498724621', '5', 'nayandahalli'),
    (5, 'EEE', 'vishwas@gmail.com', 'vishwas', 'Male', 'Gavc', '1657972145', '4', 'Holalkere,chitradurga'),
    (6, 'CIVIL', 'surya34@gmail.com', 'Surya', 'Male', 'Sans', '1452639875', '5', 'Summanahalli, Bengaluru'),
    (7, 'CSE', 'surabhi397@gmail.com', 'Surabhi', 'Female', 'Rack', '4562879622', '5', 'Sunkadakatte'),
    (8, 'ECE', 'sraya@gmail.com', 'raya', 'Male', 'sr', '5465879123', '7', 'Bengaluru'),
    (9, 'ISE', 'tijcg@gmail.com', 'tickt', 'Female', 'ds', '1654987254', '5', 'Bengaluru'),
    (10, 'CIVIL', 'fgdt@gmail.com', 'shyu', 'Male', 'sgf', '8798148524', '5', 'Bengaluru'),
    (12, 'ME', 'abde@gmail.com', 'abde', 'Male', 'vil', '4798312254', '4', 'Bengaluru'),
    (17, 'ME', 'riya@gmail.com', 'Riya', 'Female', 'heen', '1555648895', '1', 'Neelam'),
    (18, 'ECE', 'ashwini@gmail.com', 'Ashwini', 'Female', 'saah', '6546845544', '3', 'Kamakshipalya'),
    (19, 'ME', 'jagruti@gmail.com', 'Jagruthi', 'Female', 'kumari', '5151665445', '4', 'Chikkamagaluru'),
    (20, 'EEE', 'vasim@gmail.com', 'Mohammed ', 'Male', 'Vasim', '8296999564', '6', 'Mahadevapura')
]

for s in students_data:
    con.run(
        "INSERT INTO students (id, department, email, first_name, gender, last_name, phone, semester, address) "
        "VALUES (:id, :dept, :email, :fname, :gender, :lname, :phone, :sem, :addr) "
        "ON CONFLICT (id) DO NOTHING",
        id=s[0], dept=s[1], email=s[2], fname=s[3], gender=s[4], lname=s[5], phone=s[6], sem=s[7], addr=s[8]
    )
print("Students inserted.")

# 4. Insert Attendance
attendance_data = [
    (1, '2026-07-04', 'Absent', 3),
    (2, '2026-07-04', 'Present', 5),
    (3, '2026-07-04', 'Absent', 7),
    (4, '2026-07-04', 'Present', 6),
    (5, '2026-07-04', 'Present', 1),
    (6, '2026-07-04', 'Present', 12)
]

for a in attendance_data:
    con.run(
        "INSERT INTO attendance (id, attendance_date, status, student_id) "
        "VALUES (:id, :adate, :status, :sid) "
        "ON CONFLICT (id) DO NOTHING",
        id=a[0], adate=a[1], status=a[2], sid=a[3]
    )
print("Attendance inserted.")

# 5. Insert Fees
fees_data = [
    (1, 0.0, 120000.0, '2026-07-04', 'Cash', 'Paid', 'He is good', 1, 120000.0, 1),
    (2, 120000.0, 0.0, '2026-07-04', 'UPI', 'Pending', 'no', 3, 120000.0, 6)
]

for fee in fees_data:
    con.run(
        "INSERT INTO fees (id, balance_amount, paid_amount, payment_date, payment_mode, payment_status, remarks, semester, total_fee, student_id) "
        "VALUES (:id, :bal, :paid, :pdate, :pmode, :pstatus, :rem, :sem, :tot, :sid) "
        "ON CONFLICT (id) DO NOTHING",
        id=fee[0], bal=fee[1], paid=fee[2], pdate=fee[3], pmode=fee[4], pstatus=fee[5], rem=fee[6], sem=fee[7], tot=fee[8], sid=fee[9]
    )
print("Fees inserted.")

# 6. Insert Marks
marks_data = [
    (1, 47, 'A+', 43, 'Pass', 'ML', 90, 1),
    (2, 41, 'A', 47, 'Pass', 'AI', 88, 5),
    (3, 43, 'A', 37, 'Pass', 'cloud computing', 80, 3),
    (4, 16, 'F', 17, 'Fail', 'AI', 33, 8),
    (5, 17, 'F', 13, 'Fail', 'cloud computing', 30, 10),
    (6, 36, 'B', 43, 'Pass', 'DSA', 79, 12),
    (7, 29, 'D', 26, 'Pass', 'ML', 55, 10),
    (8, 45, 'A+', 47, 'Pass', 'DSA', 92, 1)
]

for m in marks_data:
    con.run(
        "INSERT INTO marks (id, external_marks, grade, internal_marks, result, subject, total_marks, student_id) "
        "VALUES (:id, :ext, :grade, :intr, :res, :subj, :tot, :sid) "
        "ON CONFLICT (id) DO NOTHING",
        id=m[0], ext=m[1], grade=m[2], intr=m[3], res=m[4], subj=m[5], tot=m[6], sid=m[7]
    )
print("Marks inserted.")

# 7. Insert Results
results_data = [
    (1, 8.4, 3, 7, 'Pass', 1, 8.7, 18, 1),
    (2, 5.1, 57, 102, 'Fail', 2, 5.7, 13, 8)
]

for r in results_data:
    con.run(
        "INSERT INTO results (id, cgpa, class_rank, department_rank, result_status, semester, sgpa, total_credits, student_id) "
        "VALUES (:id, :cgpa, :crank, :drank, :status, :sem, :sgpa, :credits, :sid) "
        "ON CONFLICT (id) DO NOTHING",
        id=r[0], cgpa=r[1], crank=r[2], drank=r[3], status=r[4], sem=r[5], sgpa=r[6], credits=r[7], sid=r[8]
    )
print("Results inserted.")

# Update Sequences so next insert auto-generates ID correctly
tables = ['students', 'faculty', 'courses', 'attendance', 'fees', 'marks', 'results', 'users']
for t in tables:
    con.run(f"SELECT setval(pg_get_serial_sequence('{t}', 'id'), COALESCE((SELECT MAX(id) FROM {t}), 1));")
print("Sequences updated.")

con.close()
print("All initial data successfully seeded!")
