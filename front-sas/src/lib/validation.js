const checkDateTime = (date, time, type) => {
  if ((date && !time) || (!date && time)) {
    alert(`please insert ${date ? `${type} time` : `${type} date`}`);
    return false;
  }
  return true;
};

const checkLength = (str, limit, name) => {
  if (str.length > limit) {
    alert(`${name} is max!`);
    return false;
  }
  return true;
};

const checkDate = (date1, date2, type) => {
  console.log(date1 < date2);
  if (date1 < date2 && date1 !== null) {
    alert(`${type} date cannot be past!!`);
    return false;
  }
  return true;
};
export { checkDateTime, checkDate, checkLength };
