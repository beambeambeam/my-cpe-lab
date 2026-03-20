-- 01) รายได้ต่อเดือนแยกตามประเภทคอนเทนต์
WITH monthly_type_revenue AS (
    SELECT
        DATE_TRUNC('month', o.created_at) AS month_start,
        c.content_type,
        SUM(oi.price) AS total_revenue,
        COUNT(DISTINCT o.id) AS paid_orders
    FROM "order" o
    JOIN order_item oi ON oi.order_id = o.id
    JOIN content c ON c.id = oi.content_id
    WHERE o.status = 'PAID'
    GROUP BY DATE_TRUNC('month', o.created_at), c.content_type
),
monthly_avg AS (
    SELECT
        month_start,
        AVG(total_revenue) AS avg_revenue
    FROM monthly_type_revenue
    GROUP BY month_start
)
SELECT
    TO_CHAR(mtr.month_start, 'YYYY-MM') AS month,
    mtr.content_type,
    mtr.total_revenue AS total_revenue_thb,
    mtr.paid_orders,
    'Above Avg' AS performance
FROM monthly_type_revenue mtr
JOIN monthly_avg ma ON ma.month_start = mtr.month_start
WHERE mtr.total_revenue > ma.avg_revenue
ORDER BY month DESC, total_revenue_thb DESC;
