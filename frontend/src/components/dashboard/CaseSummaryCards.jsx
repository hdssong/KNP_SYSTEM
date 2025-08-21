// 변경됨!! src/components/dashboard/RecentCasesList.jsx
import React from "react";
import styled from "styled-components";
import {
  Box,
  Card,
  CardContent,
  Typography,
  Divider,
  List,
  ListItem,
  ListItemText,
  Chip,
} from "@mui/material";
import { Gavel } from "@mui/icons-material";

/** props
 * items: Array<{
 *   id: string;          // 접수번호 (예: R-2024-0001)
 *   type: string;        // 죄명/종별 (예: 절도)
 *   date: string;        // 접수일 (YYYY-MM-DD)
 *   status: string;      // 완료/진행중/보류 등 (한글)
 *   priority?: 'high'|'medium'|'low';
 * }>
 * title?: string
 * maxItems?: number        // 상위 N개만 표시 (기본 5)
 * onItemClick?: (id)=>void // 행 클릭 핸들러
 */

const ListCard = styled(Card)`
  background: linear-gradient(135deg, #ffffff 0%, #f8f9ff 100%);
  border-radius: 12px !important;
  box-shadow: 0 4px 20px rgba(102, 126, 234, 0.1) !important;
  height: 100%; /* ✅ 부모 높이를 가득 */
  display: flex; /* ✅ 내부를 세로 flex */
  flex-direction: column;
`;

const StatusChip = styled(Chip)`
  font-weight: 600 !important;
  border-radius: 20px !important;
`;

function getStatusColor(status = "") {
  switch (status) {
    case "완료":
      return "#4caf50";
    case "진행중":
      return "#2196f3";
    case "보류":
      return "#ff9800";
    default:
      return "#9e9e9e";
  }
}

export default function CaseSummaryCards({ items = [], maxItems = 5, onItemClick, sx }) {
  const data = items.slice(0, maxItems);
  const clickable = typeof onItemClick === "function";

  return (
    <ListCard sx={{ ...sx }}>
      <CardContent sx={{ p: 3, flex: 1, display: "flex", flexDirection: "column", minHeight: 0 }}>
        <List sx={{ flex: 1, overflowY: "auto", minHeight: 0 }}>
          {data.map((item, idx) => (
            <React.Fragment key={item.id}>
              <ListItem
                sx={{
                  px: 0,
                  cursor: clickable ? "pointer" : "default",
                  "&:hover": clickable ? { bgcolor: "#f7f8ff" } : undefined,
                }}
                onClick={clickable ? () => onItemClick(item.id) : undefined}
              >
                <ListItemText
                  primary={
                    <Box sx={{ display: "flex", alignItems: "center", gap: 1 }}>
                      <Typography variant="subtitle1" fontWeight={700}>
                        {item.id}
                      </Typography>
                      <StatusChip
                        label={item.status || "-"}
                        size="small"
                        sx={{
                          bgcolor: getStatusColor(item.status),
                          color: "white",
                        }}
                      />
                    </Box>
                  }
                  secondary={
                    <Typography variant="body2" color="text.secondary">
                      {item.type} • {item.date}
                    </Typography>
                  }
                />
              </ListItem>

              {idx < data.length - 1 && <Divider />}
            </React.Fragment>
          ))}

          {data.length === 0 && (
            <Typography color="text.secondary" align="center" sx={{ py: 4 }}>
              표시할 사건이 없습니다.
            </Typography>
          )}
        </List>
      </CardContent>
    </ListCard>
  );
}
