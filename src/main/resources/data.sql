INSERT INTO m_modalities (m_roomnr, m_type)
SELECT 101, 'MRI' WHERE NOT EXISTS (SELECT 1 FROM m_modalities WHERE m_type = 'MRI');

INSERT INTO m_modalities (m_roomnr, m_type)
SELECT 102, 'CT' WHERE NOT EXISTS (SELECT 1 FROM m_modalities WHERE m_type = 'CT');

INSERT INTO m_modalities (m_roomnr, m_type)
SELECT 103, 'X-Ray' WHERE NOT EXISTS (SELECT 1 FROM m_modalities WHERE m_type = 'X-Ray');

INSERT INTO m_modalities (m_roomnr, m_type)
SELECT 104, 'Ultrasound' WHERE NOT EXISTS (SELECT 1 FROM m_modalities WHERE m_type = 'Ultrasound');

INSERT INTO m_modalities (m_roomnr, m_type)
SELECT 105, 'PET-Scan' WHERE NOT EXISTS (SELECT 1 FROM m_modalities WHERE m_type = 'PET-Scan');