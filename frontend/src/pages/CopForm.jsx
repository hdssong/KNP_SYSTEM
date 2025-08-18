// 변경됨!! src/pages/CaseCreateForm.jsx
import { useEffect, useMemo, useState } from "react";
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
import dayjs from "dayjs";

const Wrapper = styled(Paper)`
  /* 변경됨!!: 레이아웃 안에서 가운데 배치용 */
  width: 100%;
  max-width: 840px;
  margin: 0 auto;
  padding: 24px;
  box-shadow: none;
  background: #fff;
`;

const Row = styled(Stack)`
  /* 변경됨!!: 행 간격 통일 */
  margin-top: 16px;
`;

const crimeTypes = ["변사", "강도", "절도", "스토킹", "살인", "성범죄", "주취자", "교통"];

export default function CopForm() {
  // 변경됨!!: 자동 접수번호 생성 (더미 로직)
  const autoReportNum = useMemo(() => {
    const now = dayjs();
    const rand = String(Math.floor(Math.random() * 9000) + 1000);
    return `R-${now.format("YYYYMMDD")}-${rand}`;
  }, []);

  const [form, setForm] = useState({
    receivedAt: dayjs().format("YYYY-MM-DDTHH:mm"),
    reportNum: autoReportNum,
    receiveType: "112", // 접수 구분
    crimeType: "절도",
    content: "",
  });

  // 변경됨!!: 입력 핸들러
  const onChange = (key) => (e) => setForm((s) => ({ ...s, [key]: e.target.value }));

  const handleSubmit = (e) => {
    e.preventDefault();
    // TODO: API 연동 자리
    console.log("제출 데이터", form);
    alert("접수 완료(더미)");
  };

  const handleCancel = () => {
    // TODO: 라우팅 또는 초기화
    setForm((s) => ({ ...s, content: "" }));
    alert("작성 취소(더미)");
  };

  return (
    <Wrapper component="form" onSubmit={handleSubmit}>
      <Typography variant="h5" fontWeight={700} gutterBottom>
        사건 접수
      </Typography>
      <Typography variant="body2" color="text.secondary">
        사건 접수 정보를 입력해 주세요.
      </Typography>

      {/* 변경됨!!: 사건 접수일시 / 접수번호(자동) */}
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
          label="접수번호 (자동)"
          value={form.reportNum}
          InputProps={{ readOnly: true }}
        />
      </Row>

      {/* 변경됨!!: 접수 구분 / 사건 종별 */}
      <Row direction={{ xs: "column", sm: "row" }} spacing={2}>
        <FormControl fullWidth>
          <InputLabel id="receiveType-label">접수 구분</InputLabel>
          <Select
            labelId="receiveType-label"
            label="접수 구분"
            value={form.receiveType}
            onChange={onChange("receiveType")}
          >
            <MenuItem value="112">112</MenuItem>
            <MenuItem value="방문">방문</MenuItem>
            <MenuItem value="전화">전화</MenuItem>
            <MenuItem value="기타">기타</MenuItem>
          </Select>
        </FormControl>

        <FormControl fullWidth>
          <InputLabel id="crimeType-label">사건 종별</InputLabel>
          <Select
            labelId="crimeType-label"
            label="사건 종별"
            value={form.crimeType}
            onChange={onChange("crimeType")}
            required
          >
            {crimeTypes.map((t) => (
              <MenuItem key={t} value={t}>
                {t}
              </MenuItem>
            ))}
          </Select>
        </FormControl>
      </Row>

      {/* 변경됨!!: 신고내용 */}
      <Row spacing={2}>
        <TextField
          fullWidth
          label="신고내용"
          placeholder="신고 내용을 입력해 주세요."
          multiline
          minRows={8}
          value={form.content}
          onChange={onChange("content")}
          required
        />
      </Row>

      {/* 변경됨!!: 액션 버튼 */}
      <Row direction="row" spacing={1} justifyContent="flex-end">
        <Button variant="text" onClick={handleCancel}>
          취소
        </Button>
        <Button type="submit" variant="contained">
          접수
        </Button>
      </Row>
    </Wrapper>
  );
}
