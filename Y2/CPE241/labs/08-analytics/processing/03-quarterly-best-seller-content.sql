-- 03) คอนเทนต์ที่ถูกซื้อมากที่สุดในแต่ละไตรมาส
WITH quarterly_content AS (
    SELECT
        DATE_TRUNC('quarter', cp.purchased_at) AS quarter_start,
        c.title AS content_title,
        COUNT(*) AS purchases,
        SUM(cp.price) AS revenue_thb
    FROM content_purchase cp
    JOIN content c ON c.id = cp.content_id
    WHERE cp.status = 'PAID'
    GROUP BY DATE_TRUNC('quarter', cp.purchased_at), c.title
),
ranked AS (
    SELECT
        quarter_start,
        content_title,
        purchases,
        revenue_thb,
        RANK() OVER (
            PARTITION BY quarter_start
            ORDER BY revenue_thb DESC
        ) AS rank
    FROM quarterly_content
)
SELECT
    TO_CHAR(quarter_start, 'YYYY-"Q"Q') AS quarter,
    content_title,
    purchases,
    revenue_thb,
    rank,
    'Best Seller' AS note
FROM ranked
WHERE rank = 1
ORDER BY quarter DESC, content_title;
