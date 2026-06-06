INSERT OR IGNORE INTO doctor
(
doctor_id,
name,
department,
specialty,
password_hash
)
VALUES

(
'D001',
'王醫師',
'家醫科',
'慢性病',
'$2a$10$oaOcWPCz9Vm5weXdk4oV1../nE6cHiNiFCILqDcQa3HZuxhdDJm0e'
),

(
'D002',
'李醫師',
'內科',
'高血壓',
'$2a$10$oaOcWPCz9Vm5weXdk4oV1../nE6cHiNiFCILqDcQa3HZuxhdDJm0e'
),

(
'D003',
'陳醫師',
'復健科',
'運動傷害',
'$2a$10$oaOcWPCz9Vm5weXdk4oV1../nE6cHiNiFCILqDcQa3HZuxhdDJm0e'
),

(
'D004',
'林醫師',
'小兒科',
'兒童照護',
'$2a$10$oaOcWPCz9Vm5weXdk4oV1../nE6cHiNiFCILqDcQa3HZuxhdDJm0e'
),

(
'D005',
'張醫師',
'身心科',
'焦慮治療',
'$2a$10$oaOcWPCz9Vm5weXdk4oV1../nE6cHiNiFCILqDcQa3HZuxhdDJm0e'
);
INSERT OR IGNORE INTO patient
(
chart_no,
name,
gender,
phone,
birth_date
)

VALUES

(
'TEST00001',
'王小明',
'男',
'0912345678',
'2000-01-01'
),

(
'TEST00002',
'陳小華',
'女',
'0922333444',
'1999-05-10'
),

(
'TEST00003',
'林大同',
'男',
'0933555666',
'2001-12-25'
);
INSERT OR IGNORE INTO appointment
VALUES

(
1,
'TEST00001',
'D001',
'2026-05-24',
'上午',
'BOOKED'
),

(
2,
'TEST00002',
'D003',
'2026-05-25',
'下午',
'BOOKED'
),

(
3,
'TEST00003',
'D005',
'2026-05-26',
'晚上',
'BOOKED'
);