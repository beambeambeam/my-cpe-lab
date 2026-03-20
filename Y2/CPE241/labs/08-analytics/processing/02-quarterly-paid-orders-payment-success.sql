-- 02) จำนวนคำสั่งซื้อแบบชำระเงินรายไตรมาส และอัตราความสำเร็จของการชำระเงิน
WITH quarterly_metrics AS (
    SELECT
        DATE_TRUNC('quarter', o.created_at) AS quarter_start,
        EXTRACT(YEAR FROM o.created_at)::INT AS order_year,
        COUNT(DISTINCT o.id) AS paid_orders,
        COUNT(*) FILTER (WHERE p.status = 'SUCCESS') AS success_payments,
        COUNT(*) FILTER (WHERE p.status = 'FAILED') AS failed_payments,
        COUNT(*) FILTER (WHERE p.status IN ('SUCCESS', 'FAILED')) AS total_payment_attempts
    FROM "order" o
    LEFT JOIN payment p ON p.order_id = o.id
    WHERE o.status = 'PAID'
    GROUP BY DATE_TRUNC('quarter', o.created_at), EXTRACT(YEAR FROM o.created_at)
),
yearly_quarter_avg AS (
    SELECT
        order_year,
        AVG(paid_orders::NUMERIC) AS avg_paid_orders
    FROM quarterly_metrics
    GROUP BY order_year
)
SELECT
    TO_CHAR(qm.quarter_start, 'YYYY-"Q"Q') AS quarter,
    qm.paid_orders,
    qm.success_payments,
    qm.failed_payments,
    ROUND((qm.success_payments * 100.0) / NULLIF(qm.total_payment_attempts, 0), 2) AS success_rate_percent,
    'Below Avg' AS status,
    (qm.paid_orders - yqa.avg_paid_orders)::INT AS difference_from_avg
FROM quarterly_metrics qm
JOIN yearly_quarter_avg yqa ON yqa.order_year = qm.order_year
WHERE qm.paid_orders < yqa.avg_paid_orders
ORDER BY quarter;
