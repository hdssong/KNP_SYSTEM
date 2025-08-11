// 변경됨!! src/pages/DashBoard.jsx
import { Box, Typography, Paper } from "@mui/material";

export default function DashBoard() {
  return (
    <Box
      sx={{
        display: "flex",
        justifyContent: "center", // 가운데 정렬
        alignItems: "center", // 세로도 가운데 (원하면 flex-start로 변경)
        minHeight: "100%", // 레이아웃이 높이를 관리하므로 기본 100%
        px: 3,
      }}
    >
      <Typography variant="h4" fontWeight={700} gutterBottom>
        대시보드
      </Typography>
    </Box>
  );
}
