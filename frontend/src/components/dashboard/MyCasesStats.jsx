// 변경됨!! src/components/dashboard/MyCasesStats.jsx
import styled from "styled-components";
import { Paper, Typography, Box } from "@mui/material";

const Wrapper = styled(Box)`
  width: 100%;
  max-width: 1200px;
  margin: 0 auto;
`;

const StatCard = styled(Paper)`
  border-radius: 16px;
  padding: 20px;
  box-shadow: none;
  display: flex;
  flex-direction: column;
  gap: 6px;
  flex: 1; /* 변경됨!! 균등 분배 */
`;

export default function MyCasesStats({
  total = 0,
  inProgress = 0,
  closed = 0,
  title = "나의 사건 요약",
}) {
  return (
    <Wrapper>
      <Typography variant="h6" fontWeight={800} sx={{ mb: 0.5 }}>
        {title}
      </Typography>
      <Typography variant="body2" color="text.secondary" sx={{ mb: 2 }}>
        최근 현황을 간단히 확인하세요.
      </Typography>

      {/* Grid 대신 Box flex 사용 */}
      <Box
        sx={{
          display: "flex",
          flexDirection: { xs: "column", sm: "row" }, // 모바일에서는 세로, sm 이상은 가로
          gap: 2,
        }}
      >
        <StatCard sx={{ bgcolor: "rgba(33, 150, 243, 0.08)" }}>
          <Typography variant="overline" color="text.secondary">
            총 사건
          </Typography>
          <Typography variant="h4" fontWeight={800}>
            {total.toLocaleString()}건
          </Typography>
          <Typography variant="caption" color="text.secondary">
            지금까지 접수/담당한 사건 수
          </Typography>
        </StatCard>

        <StatCard sx={{ bgcolor: "rgba(255, 193, 7, 0.14)" }}>
          <Typography variant="overline" color="text.secondary">
            진행 중
          </Typography>
          <Typography variant="h4" fontWeight={800}>
            {inProgress.toLocaleString()}건
          </Typography>
          <Typography variant="caption" color="text.secondary">
            아직 종결되지 않은 건
          </Typography>
        </StatCard>

        <StatCard sx={{ bgcolor: "rgba(76, 175, 80, 0.14)" }}>
          <Typography variant="overline" color="text.secondary">
            종결 사건
          </Typography>
          <Typography variant="h4" fontWeight={800}>
            {closed.toLocaleString()}건
          </Typography>
          <Typography variant="caption" color="text.secondary">
            처리 완료된 건
          </Typography>
        </StatCard>
      </Box>
    </Wrapper>
  );
}
