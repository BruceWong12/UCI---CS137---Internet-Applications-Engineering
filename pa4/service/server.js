const express = require("express");
const bodyParser = require("body-parser");
const cors = require("cors");
const path = require("path");
const multer = require('multer');
const app = express();
let options = {
  setHeaders: function (res, path, stat) {
    res.set('Access-Control-Allow-Origin', '*')
  }
}
app.use('/upload', express.static(path.join(__dirname, 'upload'), options))
const corsOptions = {
  origin: "*"
};

//配置 
const storage = multer.diskStorage({ 
//文件保存路径 注意路径必须存在
	destination: function (req, file, cb) { 
		cb(null, 'upload/') 
	},
	//修改文件名称 
	filename: function (req, file, cb) { 
		var fileFormat = (file.originalname).split(".");
		cb(null, Date.now() + "." + fileFormat[fileFormat.length - 1]); 
	} 
})
const upload = multer({ storage: storage })

app.use(cors(corsOptions));

// parse requests of content-type - application/json
app.use(bodyParser.json());

// parse requests of content-type - application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ limit: '100mb', extended: true }));

const db = require("./app/models");

db.sequelize.sync();
// // drop the table if it already exists
// db.sequelize.sync({ force: true }).then(() => {
//   console.log("Drop and re-sync db.");
// });

// simple route
app.get("/", (req, res) => {
  res.json({ message: "Welcome to bezkoder application." });
});

app.post('/api/upload', upload.single("imgs"), function (req, res) {
  res.send({
    code: 200,
    url: `http://localhost:8080/upload/${req.file.filename}`
  })
});

require("./app/routes/goods")(app);
require("./app/routes/user")(app);
require("./app/routes/category")(app);

// set port, listen for requests
const PORT = process.env.PORT || 8080;
app.listen(PORT, () => {
  console.log(`Server is running on port ${PORT}.`);
});
