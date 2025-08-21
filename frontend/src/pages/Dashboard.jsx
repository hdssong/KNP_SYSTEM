// 변경됨!! src/pages/DashBoard.jsx
import styled from "styled-components";
import { Box, Stack, Typography } from "@mui/material";
import { useNavigate } from "react-router-dom";
import MyCasesStats from "../components/dashboard/MyCasesStats";
import CaseSummaryCards from "../components/dashboard/CaseSummaryCards";
import CaseMonthlyProgress from "../components/dashboard/CaseMonthlyProgress";
import CaseTypeChart from "../components/dashboard/CaseTypeChart";

const Page = styled(Box)`
  width: 100%;
  min-height: calc(100vh - 64px - 48px);
  display: flex;
  justify-content: center;
  align-items: flex-start;
  padding: 16px 24px;
`;

const Inner = styled(Box)`
  width: 100%;
  max-width: 1200px;
`;

const RECENT = [
  { id: "R-2024-0001", type: "절도", date: "2024-12-12", status: "완료" },
  { id: "R-2024-0002", type: "사기", date: "2025-01-09", status: "진행중" },
  { id: "R-2024-0003", type: "폭행", date: "2024-09-04", status: "보류" },
  { id: "R-2024-0004", type: "강도", date: "2024-11-01", status: "완료" },
  { id: "R-2024-0005", type: "성폭력", date: "2024-12-28", status: "진행중" },
];

const itemsForMonthly = RECENT.map((r) => ({
  id: r.id,
  type: r.type,
  openedAt: r.date,
  closedAt:
    r.status === "완료"
      ? // 예시로 접수일 + 20일을 종결일로 부여 (실제에선 서버 값 사용)
        new Date(new Date(r.date).getTime() + 1000 * 60 * 60 * 24 * 20).toISOString().slice(0, 10)
      : "",
  status: r.status,
}));

export default function DashBoard() {
  const navigate = useNavigate();

  // 상단 숫자 요약
  const stats = { total: 35, inProgress: 12, closed: 20 };

  return (
    <Page>
      <Inner>
        {/* 1) 상단 숫자 요약 */}
        <MyCasesStats {...stats} />

        {/* 2) 하단 레이아웃: 좌측 1 / 우측 2 */}
        <Box
          sx={{
            display: "grid",
            gridTemplateColumns: {
              xs: "1fr",
              md: "calc((min(100%, 1200px) - 32px) / 3) 1fr",
            },
            columnGap: 2,
            rowGap: 2,
            alignItems: "stretch",
            mt: 3,
            minHeight: 650,
          }}
        >
          {/* 좌측: 최근 사건 */}
          <Box sx={{ display: "flex", flexDirection: "column", minHeight: 0 }}>
            <Typography variant="h6" fontWeight={700} sx={{ mb: 1 }}>
              최근 사건 목록
            </Typography>
            <CaseSummaryCards
              items={RECENT}
              maxItems={5}
              hideTitle // 변경됨!! 카드 내부 타이틀 제거
              sx={{ flex: 1, minHeight: 0 }}
            />
          </Box>

          {/* 우측: 진행률 + 도넛 */}
          <Box sx={{ display: "flex", flexDirection: "column", minHeight: 0 }}>
            {/* 위쪽: 사건 진행률 */}
            <Box
              sx={{
                display: "flex",
                flexDirection: "column",
                flex: 1,
                minHeight: 0,
              }}
            >
              <Typography variant="h6" fontWeight={700} sx={{ mb: 1 }}>
                지난 달 사건 진행률
              </Typography>
              <CaseMonthlyProgress
                items={itemsForMonthly}
                hideTitle // 변경됨!!
                sx={{ flex: 1, minHeight: 0 }}
              />
            </Box>

            {/* 아래쪽: 사건 유형 분포 */}
            <Box
              sx={{
                display: "flex",
                flexDirection: "column",
                flex: 2,
                minHeight: 0,
                mt: 2,
              }}
            >
              <Typography variant="h6" fontWeight={700} sx={{ mb: 1 }}>
                사건 유형별 분포
              </Typography>
              <CaseTypeChart
                items={RECENT}
                hideTitle // 변경됨!!
                sx={{ flex: 1, minHeight: 0 }}
              />
            </Box>
          </Box>
        </Box>
      </Inner>
    </Page>
  );
}
