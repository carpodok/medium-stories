const express = require("express");
const jwt = require("jsonwebtoken");
const { users } = require("../utils/data");

const router = express.Router();

router.post("/login", (req, res) => {
  const { username, password } = req.body;

  const user = users.find(
    (u) => u.username === username && u.password === password
  );
  if (!user) {
    return res.status(404).json({ msg: "Invalid credentials" });
  }

  const payload = { id: user.id, role: user.role };
  const token = jwt.sign(payload, process.env.JWT_SECRET, { expiresIn: "1h" });

  return res.json({ token });
});

module.exports = router;
