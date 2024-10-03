const express = require("express");
const resourceRoutes = require("./routes/resource.route");
const authRoutes = require("./routes/auth.route");
const dotenv = require("dotenv");

dotenv.config();
const app = express();
app.use(express.json());

// Routes
app.use("/api/resources", resourceRoutes);
app.use("/api/auth", authRoutes);
app.use("/", (req, res) => {
  res.send("Hello RBAC App");
});

const PORT = process.env.PORT || 5000;

app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});
