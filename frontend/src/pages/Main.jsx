import { Typography, Box } from "@mui/material";

const Home = () => {
  return (
    <Box
      sx={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        height: "100vh",
      }}
    >
      <Typography variant="h3" component="h1" sx={{ fontWeight: 600 }}>
        메인페이지
      </Typography>
    </Box>
  );
};

export default Home;
