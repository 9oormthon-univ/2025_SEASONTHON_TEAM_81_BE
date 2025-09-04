CREATE TABLE IF NOT EXISTS `healing_content`
(
    `healing_content_id` BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `created_at`         DATETIME(6)  NULL,
    `updated_at`         DATETIME(6)  NULL,
    `title`              VARCHAR(255) NOT NULL,
    `content`            VARCHAR(255) NOT NULL,
    `category`           VARCHAR(255) NULL
);

-- 데이터가 이미 존재하면 무시하고 삽입
INSERT IGNORE INTO `healing_content` (healing_content_id, title, category, content)
VALUES (1, '감사 인사', 'GRATITUDE_THANKS', '오늘 감사했던 일 3가지를 떠올려요.'),
       (2, '칭찬하기', 'PRAISE', '오늘 잘한 1가지를 한 문장으로 칭찬해요.'),
       (3, '짧은 명상', 'SHORT_MEDITATION', '안전한 곳에 멈춰 1분 동안 호흡에만 집중해요.'),
       (4, '색깔 찾기', 'FIND_COLOR', '무지개 색 중 한 색을 골라, 주변의 그 색이 보이는 것 3가지를 찾아요.'),
       (5, '스트레칭', 'STRETCHING', '목·어깨·손목을 천천히 늘려 긴장을 풀어요.'),
       (6, '리듬 워킹', 'RHYTHM_WALKING', '좋아하는 멜로디를 흥얼거리며 걸음에 맞춰 걸어요.');