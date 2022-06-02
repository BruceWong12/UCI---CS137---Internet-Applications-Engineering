import dayjs from "dayjs";
const format = (str) => dayjs(str).format("YYYY-MM-DD HH:mm:ss");
export default format;
