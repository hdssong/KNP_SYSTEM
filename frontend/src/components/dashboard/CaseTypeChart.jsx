// 변경됨!! src/components/dashboard/CaseTypeDonut.jsx
import styled from "styled-components";
import { Box, Card, CardContent, Stack, Typography } from "@mui/material";

/** Props
 * - items?: Array<{ id: string; type: string; date?: string; status?: string }>
 * - data?:  Array<{ label: string; value: number }>
 *   => 둘 중 하나만 넘기면 됩니다. items가 오면 type으로 자동 집계합니다.
 * - title?: string
 */
const Panel = styled(Card)`
  background: linear-gradient(180deg, #ffffff 0%, #f8f9ff 100%);
  border-radius: 16px !important;
  box-shadow: 0 8px 28px rgba(102, 126, 234, 0.12) !important;
`;

const Donut = styled(Box)`
  position: relative;
  width: 220px;
  height: 220px;
  border-radius: 50%;
  margin: 0 auto;
  /* conic-gradient로 도넛 채우기 (JS에서 동적으로 설정) */
`;

const DonutHole = styled(Box)`
  position: absolute;
  top: 50%;
  left: 50%;
  width: 120px;
  height: 120px;
  transform: translate(-50%, -50%);
  background: #fff;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  text-align: center;
`;

const LegendDot = styled("span")(({ color }) => ({
  display: "inline-block",
  width: 10,
  height: 10,
  borderRadius: "50%",
  background: color,
  marginRight: 8,
}));

const COLORS = ["#42A5F5", "#FFCA28", "#EF5350", "#8E24AA", "#66BB6A", "#26C6DA", "#AB47BC"];

function aggregateByType(items) {
  const map = new Map();
  items.forEach((it) => map.set(it.type, (map.get(it.type) || 0) + 1));
  return Array.from(map, ([label, value]) => ({ label, value }));
}

export default function CaseTypeChart({ items = [], data, sx }) {
  const src = (data && data.length ? data : aggregateByType(items)).filter((d) => d.value > 0);

  const total = src.reduce((a, b) => a + b.value, 0) || 1;
  const parts = src.map((d, i) => ({
    ...d,
    color: COLORS[i % COLORS.length],
    pct: Math.round((d.value / total) * 100),
  }));

  // conic-gradient 스트링 만들기
  let acc = 0;
  const segments = parts.map((p) => {
    const start = acc;
    const end = acc + (p.value / total) * 360;
    acc = end;
    return `${p.color} ${start}deg ${end}deg`;
  });
  const gradient = `conic-gradient(${segments.join(", ")})`;

  return (
    <Panel sx={{ height: "100%", display: "flex", flexDirection: "column", ...sx }}>
      <CardContent sx={{ flex: 1, minHeight: 0, display: "flex", flexDirection: "column" }}>
        <Typography variant="body2" color="text.secondary" sx={{ mb: 2 }}>
          유형별 사건 비율
        </Typography>
        <Stack
          direction={{ xs: "column", sm: "row" }}
          spacing={2}
          alignItems="center"
          justifyContent="space-between"
          sx={{ flex: 1, minHeight: 0 }}
        >
          {/* Donut */}
          <Box
            sx={{
              position: "relative",
              flex: 1,
              minWidth: 0,
              display: "flex",
              justifyContent: "center",
            }}
          >
            {" "}
            <Donut sx={{ background: gradient }} />
            <DonutHole>
              <Box>
                <Typography variant="caption" color="text.secondary">
                  총 사건
                </Typography>
                <Typography variant="h5" fontWeight={800}>
                  {total}
                </Typography>
              </Box>
            </DonutHole>
          </Box>

          {/* Legend */}
          <Box sx={{ minWidth: 220, flexShrink: 0 }}>
            <Stack spacing={1.2}>
              {parts.map((p) => (
                <Stack
                  key={p.label}
                  direction="row"
                  alignItems="center"
                  justifyContent="space-between"
                >
                  <Box sx={{ display: "flex", alignItems: "center" }}>
                    <LegendDot color={p.color} />
                    <Typography variant="body2">{p.label}</Typography>
                  </Box>
                  <Typography variant="body2" fontWeight={700}>
                    {p.pct}%{" "}
                    <span style={{ color: "#9AA0A6", fontWeight: 400 }}>({p.value}건)</span>
                  </Typography>
                </Stack>
              ))}
              {parts.length === 0 && (
                <Typography variant="body2" color="text.secondary">
                  데이터가 없습니다.
                </Typography>
              )}
            </Stack>
          </Box>
        </Stack>
      </CardContent>
    </Panel>
  );
}
