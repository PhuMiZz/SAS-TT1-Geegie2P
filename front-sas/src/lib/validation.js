const checkDateTime = (date, time, type) => {
  if ((date && !time) || (!date && time)) {
    alert(`please insert ${date ? `${type} time` : `${type} date`}`);
    return false;
  }
  return true;
};

const checkLength = (str, limit, name) => {
  if (str.length > limit) {
    if (name === "Announcement title") alert(`size must be between 1 and 200`);
    else if (name === "Announcement description")
      alert(`size must be between 1 and 10000`);
    return false;
  }
  return true;
};

const checkDate = (date1, date2, type) => {
  if (date1 < date2 && date1 !== null) {
    alert(`${type} must be a date in the present or in the future`);
    return false;
  }
  return true;
};
export { checkDateTime, checkDate, checkLength };
