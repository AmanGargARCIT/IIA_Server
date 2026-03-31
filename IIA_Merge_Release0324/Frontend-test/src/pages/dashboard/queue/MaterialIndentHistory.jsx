import { Modal, Table, Spin, Tag } from 'antd';
import { useEffect, useState } from 'react';
import axios from 'axios';
import { HistoryOutlined } from '@ant-design/icons';

const MaterialHistory = ({ materialCode, open, onCancel, historyType }) => {
  const [history, setHistory] = useState([]);
  const [loading, setLoading] = useState(false);

  const isJob = historyType === 'job';

  const columns = [
    {
      title: 'Indent ID',
      dataIndex: 'indentId',
      key: 'indentId',
      render: (text) => <Tag color="blue">{text}</Tag>,
    },
    {
      title: 'User ID',
      dataIndex: 'userId',
      key: 'userId',
      render: (text) => text || '--',
    },
  ];

  useEffect(() => {
    const fetchHistory = async () => {
      if (open && materialCode) {
        setLoading(true);
        try {
          const endpoint = isJob
            ? `/api/indents/jobHistory/${materialCode}`
            : `/api/indents/materialHistory/${materialCode}`;
          const response = await axios.get(endpoint);

          const historyData = Array.isArray(response?.data?.responseData)
            ? response.data.responseData
            : [];

          setHistory(historyData);
        } catch (error) {
          console.error('Error fetching history:', error);
          setHistory([]);
        } finally {
          setLoading(false);
        }
      }
    };

    fetchHistory();
  }, [open, materialCode, isJob]);

  return (
    <Modal
      title={
        <div className="flex items-center gap-2">
          <HistoryOutlined />
          {isJob ? `Indent History for Job: ${materialCode}` : `Indent History for Material: ${materialCode}`}
        </div>
      }
      open={open}
      onCancel={onCancel}
      footer={null}
      width={600}
    >
      <Spin spinning={loading}>
        <Table
          dataSource={history}
          columns={columns}
          rowKey="indentId"
          pagination={false}
          bordered
          size="small"
          locale={{ emptyText: 'No material history found' }}
        />
      </Spin>
    </Modal>
  );
};

export default MaterialHistory;
