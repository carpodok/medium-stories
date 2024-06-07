require("dotenv").config();
const express = require("express");
const mongoose = require("mongoose");
const jwt = require("jsonwebtoken");
const bcrypt = require("bcryptjs");
const User = require("./models/User");

const app = express();
const port = process.env.PORT || 5000;

app.use(express.json());

mongoose
  .connect(process.env.MONGO_URI)
  .then(() => console.log("MongoDB connected"))
  .catch((err) => console.log(err));

const generateAccessToken = (user) => {
  return jwt.sign(user, process.env.ACCESS_TOKEN_SECRET, { expiresIn: "15m" });
};

const generateRefreshToken = (user) => {
  return jwt.sign(user, process.env.REFRESH_TOKEN_SECRET);
};

let refreshTokens = [];

app.post("/register", async (req, res) => {
  const { username, password } = req.body;
  try {
    const newUser = new User({ username, password });
    await newUser.save();
    res.status(201).send("User registered");
  } catch (err) {
    res.status(400).send("Error registering user");
  }
});

app.post("/login", async (req, res) => {
  const { username, password } = req.body;
  const user = await User.findOne({ username });
  if (!user) return res.sendStatus(401);

  const isMatch = await bcrypt.compare(password, user.password);
  if (!isMatch) return res.sendStatus(401);

  const accessToken = generateAccessToken({ id: user._id });
  const refreshToken = generateRefreshToken({ id: user._id });
  refreshTokens.push(refreshToken);
  res.json({ accessToken, refreshToken });
});

app.post("/token", (req, res) => {
  const { token } = req.body;
  if (!token) return res.sendStatus(401);
  if (!refreshTokens.includes(token)) return res.sendStatus(403);

  jwt.verify(token, process.env.REFRESH_TOKEN_SECRET, (err, user) => {
    if (err) return res.sendStatus(403);
    const accessToken = generateAccessToken({ id: user.id });
    res.json({ accessToken });
  });
});

app.post("/logout", (req, res) => {
  const { token } = req.body;
  refreshTokens = refreshTokens.filter((t) => t !== token);
  res.sendStatus(204);
});

app.listen(port, () => {
  console.log(`Server running on port ${port}`);
});
