// 신규!! src/components/dashboard/CaseMonthlyProgress.jsx
import styled from "styled-components";
import {
  Box,
  Card,
  CardContent,
  Typography,
  LinearProgress,
  Stack,
  Chip,
  Divider,
} from "@mui/material";

const Panel = styled(Card)`
  background: linear-gradient(180deg, #ffffff 0%, #f8f9ff 100%);
  border-radius: 16px !important;
  box-shadow: 0 8px 28px rgba(102, 126, 234, 0.12) !important;
`;

// 날짜 문자열('YYYY-MM-DD') → Date
const toDate = (s) => (s ? new Date(s + "T00:00:00") : null);
// 지난 달 'YYYY-MM'
const lastMonthKey = () => {
  const now = new Date();
  const d = new Date(now.getFullYear(), now.getMonth() - 1, 1);
  return d.toISOString().slice(0, 7);
};
const inMonth = (dateStr, ym) => {
  if (!dateStr) return false;
  return dateStr.slice(0, 7) === ym;
};

/** props
 * items: Array<{
 *   id: string;
 *   type: string;
 *   openedAt: string;  // 접수일 'YYYY-MM-DD'
 *   closedAt?: string; // 종결일 'YYYY-MM-DD'
 *   status?: string;   // 완료/진행중 등 (옵션)
 * }>
 * month?: string       // 'YYYY-MM' (기본: 지난 달)
 */
export default function CaseMonthlyProgress({ items = [], month = lastMonthKey(), sx }) {
  // 지난 달 지표 계산
  const opened = items.filter((it) => inMonth(it.openedAt, month));
  const closed = items.filter((it) => inMonth(it.closedAt, month));

  const openedCount = opened.length;
  const closedCount = closed.length;

  const progress = openedCount ? Math.round((closedCount / openedCount) * 100) : 0;

  // 평균 처리일(지난 달에 종결된 건만 대상)
  const closedWithDur = closed
    .map((it) => {
      const o = toDate(it.openedAt);
      const c = toDate(it.closedAt);
      return o && c ? Math.max(0, Math.round((c - o) / 86400000)) : null;
    })
    .filter((v) => v !== null);
  const avgDays = closedWithDur.length
    ? Math.round(closedWithDur.reduce((a, b) => a + b, 0) / closedWithDur.length)
    : 0;

  // 월 텍스트
  const monthText = `${month} (지난 달)`;

  return (
    <Panel sx={{ height: "100%", display: "flex", flexDirection: "column", ...sx }}>
      <CardContent sx={{ flex: 1, minHeight: 0, display: "flex", flexDirection: "column" }}>
        {" "}
        <Typography variant="body2" color="text.secondary" sx={{ mb: 2 }}>
          기준: {monthText}
        </Typography>
        {/* KPI 칩 3종 */}
        <Stack direction="row" spacing={1} sx={{ mb: 2 }} flexWrap="wrap">
          <Chip color="primary" variant="outlined" label={`신규 ${openedCount}건`} />
          <Chip color="success" variant="outlined" label={`종결 ${closedCount}건`} />
          <Chip color="default" variant="outlined" label={`평균 ${avgDays}일`} />
        </Stack>
        {/* 진행률 바 */}
        <Box sx={{ mb: 1, display: "flex", justifyContent: "space-between", alignItems: "center" }}>
          <Typography variant="body2">진행률(종결 ÷ 신규)</Typography>
          <Typography variant="body2" fontWeight={700}>
            {progress}%
          </Typography>
        </Box>
        <LinearProgress
          variant="determinate"
          value={progress}
          sx={{
            height: 10,
            borderRadius: 5,
            bgcolor: "rgba(0,0,0,0.06)",
            "& .MuiLinearProgress-bar": { bgcolor: "#667eea" },
          }}
        />
      </CardContent>
    </Panel>
  );
}
