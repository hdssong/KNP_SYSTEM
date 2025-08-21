// 변경됨!! src/pages/DetectiveForm.jsx
import { useMemo, useState } from "react";
import styled from "styled-components";
import {
  Box,
  Paper,
  Typography,
  TextField,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
  Button,
  Stack,
} from "@mui/material";

/* ---------- styled ---------- */
const Wrapper = styled(Paper)`
  width: 100%;
  max-width: 840px;
  margin: 0 auto;
  padding: 24px;
  box-shadow: none;
  background: #fff;
`;

const Row = styled(Stack)`
  margin-top: 16px;
`;

/* ---------- helpers ---------- */
// datetime-local 기본값 생성
function toDatetimeLocal(date = new Date()) {
  const tz = date.getTimezoneOffset() * 60000;
  return new Date(date - tz).toISOString().slice(0, 16);
}

// 사건 종별 목록
const CRIME_TYPES = ["변사", "강도", "절도", "스토킹", "살인", "성범죄", "주취자", "교통"];

// 랜덤 접수번호 생성 함수
function generateReportNum() {
  const now = new Date();
  const dateStr = now.toISOString().slice(0, 10).replace(/-/g, ""); // YYYYMMDD
  const rand = String(Math.floor(Math.random() * 9000) + 1000); // 4자리 랜덤 숫자
  return `R-${dateStr}-${rand}`;
}

export default function DetectiveForm() {
  // 폼 로드 시 한 번만 랜덤 번호 생성
  const randomReport = useMemo(() => generateReportNum(), []);

  const [form, setForm] = useState({
    receivedAt: toDatetimeLocal(),
    crimeType: "절도",
    reportNum: randomReport,
    content: "",
  });

  const onChange = (key) => (e) => setForm((s) => ({ ...s, [key]: e.target.value }));

  const handleCancel = () => {
    setForm((s) => ({ ...s, content: "" }));
    alert("작성 취소(더미)");
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("조서 제출 데이터:", form);
    alert("조서가 제출되었습니다(더미).");
  };

  return (
    <Wrapper component="form" onSubmit={handleSubmit}>
      <Typography variant="h5" fontWeight={700} gutterBottom>
        형사 조서 작성
      </Typography>
      <Typography variant="body2" color="text.secondary">
        사건 접수 정보와 조서 내용을 입력하세요.
      </Typography>

      {/* 사건 접수 일시 & 접수번호(랜덤 자동 배정) */}
      <Row direction={{ xs: "column", sm: "row" }} spacing={2}>
        <TextField
          fullWidth
          label="사건 접수일시"
          type="datetime-local"
          value={form.receivedAt}
          onChange={onChange("receivedAt")}
          InputLabelProps={{ shrink: true }}
          required
        />
        <TextField
          fullWidth
          label="접수번호 (자동 배정)"
          value={form.reportNum}
          InputProps={{ readOnly: true }}
        />
      </Row>

      {/* 사건 종별 */}
      <Row direction={{ xs: "column", sm: "row" }} spacing={2}>
        <FormControl fullWidth>
          <InputLabel id="crimeType-label">사건 종별</InputLabel>
          <Select
            labelId="crimeType-label"
            label="사건 종별"
            value={form.crimeType}
            onChange={onChange("crimeType")}
            required
          >
            {CRIME_TYPES.map((t) => (
              <MenuItem key={t} value={t}>
                {t}
              </MenuItem>
            ))}
          </Select>
        </FormControl>
      </Row>

      {/* 조사 내용 */}
      <Row spacing={2}>
        <TextField
          fullWidth
          label="조사 내용"
          placeholder="조사 내용을 입력하세요."
          multiline
          minRows={8}
          value={form.content}
          onChange={onChange("content")}
          required
        />
      </Row>

      {/* 버튼 */}
      <Row direction="row" spacing={1} justifyContent="flex-end">
        <Button variant="text" onClick={handleCancel}>
          취소
        </Button>
        <Button type="submit" variant="contained">
          제출
        </Button>
      </Row>
    </Wrapper>
  );
}
