import { Box, Typography, IconButton } from "@mui/material";
import GitHubIcon from "@mui/icons-material/GitHub";

function Footer() {
  return (
    <Box
      component="footer"
      sx={{
        py: 2,
        px: 4,
        display: "flex",
        alignItems: "center",
        justifyContent: "space-between", // 좌측 텍스트 / 우측 아이콘
        bgcolor: "background",
        borderTop: "1px solid",
        borderColor: "grey.300",
      }}
    >
      <Typography variant="body2" color="text.secondary">
        © 2025 사건 Issue
      </Typography>

      <Box>
        <IconButton
          color="inherit"
          href="https://github.com/hdssong/KNP_SYSTEM"
          target="_blank"
          rel="noopener noreferrer"
          sx={{
            color: "text.secondary",
            "&:hover": { color: "primary.main" }, // hover 시 색상 강조
          }}
        >
          <GitHubIcon fontSize="medium" />
        </IconButton>
      </Box>
    </Box>
  );
}

export default Footer;
