function getLocaleDateTime(dateTime) {
  const date = new Date(dateTime);
  const option = {
    day: "numeric",
    month: "short",
    year: "numeric",
    hour: "2-digit",
    minute: "2-digit",
    hour12: false,
  };
  return date.toLocaleString("en-US", option);
}

export { getLocaleDateTime };
