import React from "react";
import { Form, Input, Select, Button, DatePicker } from "antd";
import { SearchOutlined } from "@ant-design/icons";
import dayjs from "dayjs";

const { Option } = Select;
const { Search } = Input;

const CustomIndentSearch = ({
  label,
  name,
  searchType,
  setSearchType,
  searchValue,
  setSearchValue,
  onSearch,
  required,
  readOnly
}) => {
  return (
    <Form.Item
      label={label}
      name={name}
      required={required}
      style={{ marginBottom: 16 }}
    >
      <div style={{ display: "flex", gap: "16px" }}>
        <Select
          value={searchType}
          onChange={(val) => {
            setSearchType(val);
            setSearchValue(""); // clear value when switching type
          }}
          style={{ width: "60%" }}
        >
          <Option value="processId">Process ID</Option>
          <Option value="submittedDate">Submitted Date</Option>
          <Option value="materialdescription">Material Description</Option>
          <Option value="indentorname">Indentor Name</Option>
          <Option value="vendorName">Vendor Name</Option>
        </Select>

        {searchType === "submittedDate" ? (
          <div style={{ display: "flex", gap: "8px", width: "60%" }}>
            <DatePicker
              value={searchValue ? dayjs(searchValue) : null}
              onChange={(date, dateString) => setSearchValue(dateString)}
              format="YYYY-MM-DD"
              style={{ flex: 1 }}
              placeholder="Select date"
            />
            <Button
              type="primary"
              icon={<SearchOutlined />}
              onClick={onSearch}
            >
              Search
            </Button>
          </div>
        ) : (
          <Search
            value={searchValue}
            onChange={(e) => setSearchValue(e.target.value)}
            onSearch={onSearch}
            enterButton
            style={{ width: "60%" }}
          />
        )}
      </div>
    </Form.Item>
  );
};

export default CustomIndentSearch;
