const users = [
  { id: 1, username: "admin", password: "adminpass", role: "Admin" },
  { id: 2, username: "editor", password: "editorpass", role: "Editor" },
  { id: 3, username: "viewer", password: "viewerpass", role: "Viewer" },
];

const resources = [
  { id: 1, name: "Resource 1" },
  { id: 2, name: "Resource 2" },
  { id: 3, name: "Resource 3" },
];

module.exports = { users, resources };
