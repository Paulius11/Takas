import { useEffect } from "react";

//Show name of the page
function Page(props) {
  const capitalize = (str) => {
    return str.charAt(0).toUpperCase() + str.slice(1);
  };

  useEffect(() => {
    document.title = `${capitalize(props.title)} - Site Name` || "";
  }, [props.title]);
  return props.children;
}

export default Page;
