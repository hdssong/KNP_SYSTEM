// 변경됨!! src/pages/CaseList.jsx
import { useEffect, useMemo, useState } from "react";
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
import axios from "axios";

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
  { id: "reportId", label: "접수번호", minWidth: 140, align: "center" },
  { id: "caseIdnum", label: "사건번호", minWidth: 140, align: "center" },
  { id: "caseCharge", label: "죄명", minWidth: 200, align: "center" },
  { id: "caseReceiveDate", label: "사건접수일자", minWidth: 140, align: "center" },
  { id: "caseEndDate", label: "사건종결일자", minWidth: 140, align: "center" },
  { id: "ongoingDate", label: "진행일수", minWidth: 110, align: "center" },
  { id: "caseStatus", label: "진행상태", minWidth: 120, align: "center" },
];

// 상태 칩 매핑
const statusChip = (status) => {
  const map = {
    접수: { label: "접수", color: "info", variant: "outlined" },
    승인: { label: "승인", color: "secondary", variant: "outlined" },
    "진행 중": { label: "진행 중", color: "warning", variant: "outlined" },
    종결: { label: "종결", color: "success", variant: "outlined" },
    기각: { label: "기각", color: "error", variant: "outlined" },
  };

  const v = map[status] ?? { label: status, color: "default", variant: "outlined" };
  return <Chip size="small" label={v.label} color={v.color} variant={v.variant} />;
};

const formatDate = (dateStr) => {
  if (!dateStr) return "-";
  const date = new Date(dateStr);
  const y = date.getFullYear();
  const m = String(date.getMonth() + 1).padStart(2, "0");
  const d = String(date.getDate()).padStart(2, "0");
  return `${y}. ${m}. ${d}`;
};

export default function CaseList() {
  const [cases, setCases] = useState([]);
  const [q, setQ] = useState("");
  const [page, setPage] = useState(1);
  const rowsPerPage = 6;

  useEffect(() => {
    axios
      .get("/cases")
      .then((res) => {
        console.log("API응답", res.data);
        setCases(res.data);
      })
      .catch((err) => console.error(err));
  }, []);

  const filtered = useMemo(() => {
    if (!q.trim()) return cases;
    const s = q.toLowerCase();
    return cases.filter(
      (r) =>
        r.reportId.toLowerCase().includes(s) ||
        r.caseIdnum.toLowerCase().includes(s) ||
        r.caseCharge.toLowerCase().includes(s) ||
        r.caseStatus.toLowerCase().includes(s)
    );
  }, [q, cases]);

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
          placeholder="검색: 접수번호 / 사건번호 / 죄명"
          value={q}
          onChange={(e) => {
            setPage(1);
            setQ(e.target.value);
          }}
          sx={{
            width: 360,
            "& .MuiInputBase-input::placeholder": {
              fontSize: "14px",
            },
          }}
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
              <TableRow hover key={r.caseIdnum} sx={{ "& td": { textAlign: "center" } }}>
                <TableCell>{r.reportId}</TableCell>
                <TableCell>{r.caseIdnum}</TableCell>
                <TableCell sx={{ color: "primary.main", fontWeight: 600 }}>
                  {r.caseCharge}
                </TableCell>
                <TableCell align="center">{formatDate(r.caseReceiveDate) || "-"}</TableCell>
                <TableCell align="center">{formatDate(r.caseEndDate) || "-"}</TableCell>
                <TableCell align="right">{r.ongoingDate.toLocaleString()}일</TableCell>
                <TableCell align="center">{statusChip(r.caseStatus)}</TableCell>
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
