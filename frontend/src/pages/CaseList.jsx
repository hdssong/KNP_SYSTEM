// 변경됨!! src/pages/CaseList.jsx
import { useMemo, useState } from "react";
import styled from "styled-components";
import {
  Box,
  Typography,
  Paper,
  Table,
  TableHead,
  TableBody,
  TableRow,
  TableCell,
  Chip,
  TextField,
  IconButton,
  Pagination,
} from "@mui/material";
import MoreHorizIcon from "@mui/icons-material/MoreHoriz";

const Wrapper = styled(Paper)`
  width: 100%;
  max-width: 1200px; /* 레이아웃 안에서 예쁘게 */
  margin: 0 auto;
  padding: 16px 16px 8px;
  box-shadow: none; /* 변경됨!! 이미지처럼 플랫하게 */
`;

const Header = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 12px;
  padding: 8px 8px 16px;
`;

const cols = [
  { id: "report_num", label: "접수번호", minWidth: 140 },
  { id: "case_num", label: "사건번호", minWidth: 140 },
  { id: "crime_title", label: "죄명", minWidth: 200 },
  { id: "case_receive_date", label: "사건접수일자", minWidth: 140, align: "center" },
  { id: "case_end_date", label: "사건종결일자", minWidth: 140, align: "center" },
  { id: "date_plus", label: "진행일수", minWidth: 110, align: "right" },
  { id: "CaseList_status", label: "진행상태", minWidth: 120, align: "center" },
  { id: "actions", label: "", minWidth: 48, align: "right" },
];

const DUMMY = [
  {
    report_num: "R-2024-0001",
    case_num: "C-2024-001",
    crime_title: "절도",
    case_receive_date: "2024-12-12",
    case_end_date: "2025-01-03",
    date_plus: 22,
    CaseList_status: "COMPLETED",
  },
  {
    report_num: "R-2024-0002",
    case_num: "C-2024-002",
    crime_title: "사기",
    case_receive_date: "2025-01-09",
    case_end_date: "",
    date_plus: 128,
    CaseList_status: "ONGOING",
  },
  {
    report_num: "R-2024-0003",
    case_num: "C-2024-003",
    crime_title: "폭행",
    case_receive_date: "2024-09-04",
    case_end_date: "2024-12-04",
    date_plus: 92,
    CaseList_status: "INACTIVE",
  },
  {
    report_num: "R-2024-0004",
    case_num: "C-2024-004",
    crime_title: "강도",
    case_receive_date: "2024-11-01",
    case_end_date: "2024-12-01",
    date_plus: 31,
    CaseList_status: "CRITICAL",
  },
  {
    report_num: "R-2024-0005",
    case_num: "C-2024-005",
    crime_title: "성폭력",
    case_receive_date: "2024-12-28",
    case_end_date: "2025-01-28",
    date_plus: 31,
    CaseList_status: "ONGOING",
  },
  {
    report_num: "R-2025-0006",
    case_num: "C-2025-006",
    crime_title: "방화",
    case_receive_date: "2025-02-24",
    case_end_date: "",
    date_plus: 40,
    CaseList_status: "CANCELLED",
  },
  {
    report_num: "R-2025-0007",
    case_num: "C-2025-007",
    crime_title: "교통범죄",
    case_receive_date: "2025-03-12",
    case_end_date: "",
    date_plus: 63,
    CaseList_status: "ONGOING",
  },
];

// 상태 칩 매핑
const statusChip = (status) => {
  const map = {
    COMPLETED: { label: "종결", color: "success", variant: "outlined" },
    ONGOING: { label: "진행중", color: "primary", variant: "outlined" },
    INACTIVE: { label: "보류", color: "warning", variant: "outlined" },
    CRITICAL: { label: "중요", color: "error", variant: "outlined" },
    CANCELLED: { label: "취소", color: "default", variant: "outlined" },
  };
  const v = map[status] ?? { label: status, color: "default", variant: "outlined" };
  return <Chip size="small" label={v.label} color={v.color} variant={v.variant} />;
};

export default function CaseList() {
  const [q, setQ] = useState("");
  const [page, setPage] = useState(1);
  const rowsPerPage = 6;

  const filtered = useMemo(() => {
    if (!q.trim()) return DUMMY;
    const s = q.toLowerCase();
    return DUMMY.filter(
      (r) =>
        r.report_num.toLowerCase().includes(s) ||
        r.case_num.toLowerCase().includes(s) ||
        r.crime_title.toLowerCase().includes(s) ||
        r.CaseList_status.toLowerCase().includes(s)
    );
  }, [q]);

  const pageCount = Math.max(1, Math.ceil(filtered.length / rowsPerPage));
  const start = (page - 1) * rowsPerPage;
  const rows = filtered.slice(start, start + rowsPerPage);

  return (
    <Wrapper>
      <Header>
        <Typography variant="h6" sx={{ fontWeight: 700 }}>
          사건 목록
        </Typography>
        <TextField
          size="small"
          placeholder="검색: 접수번호/사건번호/죄명/상태"
          value={q}
          onChange={(e) => {
            setPage(1);
            setQ(e.target.value);
          }}
          sx={{ width: 360 }}
        />
      </Header>

      <Box
        sx={{ overflowX: "auto", borderRadius: 2, border: "1px solid #eee", background: "#fff" }}
      >
        <Table stickyHeader aria-label="case table">
          <TableHead>
            <TableRow>
              {cols.map((c) => (
                <TableCell
                  key={c.id}
                  align={c.align || "left"}
                  sx={{ minWidth: c.minWidth, fontWeight: 700, bgcolor: "#fafafa" }}
                >
                  {c.label}
                </TableCell>
              ))}
            </TableRow>
          </TableHead>

          <TableBody>
            {rows.map((r) => (
              <TableRow hover key={r.case_num}>
                <TableCell>{r.report_num}</TableCell>
                <TableCell>{r.case_num}</TableCell>
                <TableCell sx={{ color: "primary.main", fontWeight: 600 }}>
                  {r.crime_title}
                </TableCell>
                <TableCell align="center">{r.case_receive_date || "-"}</TableCell>
                <TableCell align="center">{r.case_end_date || "-"}</TableCell>
                <TableCell align="right">{r.date_plus.toLocaleString()}일</TableCell>
                <TableCell align="center">{statusChip(r.CaseList_status)}</TableCell>
                <TableCell align="right">
                  <IconButton size="small">
                    <MoreHorizIcon />
                  </IconButton>
                </TableCell>
              </TableRow>
            ))}

            {rows.length === 0 && (
              <TableRow>
                <TableCell
                  colSpan={cols.length}
                  align="center"
                  sx={{ py: 6, color: "text.secondary" }}
                >
                  데이터가 없습니다.
                </TableCell>
              </TableRow>
            )}
          </TableBody>
        </Table>
      </Box>

      <Box
        sx={{
          display: "flex",
          justifyContent: "space-between",
          alignItems: "center",
          mt: 1,
          px: 0.5,
        }}
      >
        {/* <Typography variant="body2" color="text.secondary">
          {filtered.length > 0
            ? `${start + 1}–${Math.min(start + rowsPerPage, filtered.length)} of ${filtered.length}`
            : "0 item"}
        </Typography> */}
        <Pagination
          page={page}
          count={pageCount}
          onChange={(_, p) => setPage(p)}
          shape="rounded"
          size="small"
          sx={{ my: 1 }}
        />
      </Box>
    </Wrapper>
  );
}
