const { resources } = require("../utils/data");

// Get all resources
const getResources = (req, res) => {
  res.status(200).json(resources);
};

// Create a new resource
const createResource = (req, res) => {
  const newResource = { id: resources.length + 1, name: req.body.name };
  resources.push(newResource);
  res.status(201).json({ msg: "Resource created", resource: newResource });
};

// Update an existing resource
const updateResource = (req, res) => {
  const resource = resources.find((r) => r.id == req.params.id);
  if (!resource) {
    return res.status(404).json({ msg: "Resource not found" });
  }
  resource.name = req.body.name;
  res.status(200).json({ msg: "Resource updated", resource });
};

// Delete a resource
const deleteResource = (req, res) => {
  const index = resources.findIndex((r) => r.id == req.params.id);
  if (index === -1) {
    return res.status(404).json({ msg: "Resource not found" });
  }
  resources.splice(index, 1);
  res.status(200).json({ msg: "Resource deleted" });
};

module.exports = {
  getResources,
  createResource,
  updateResource,
  deleteResource,
};
