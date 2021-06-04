/**
 * Create Time: 2021-06-01 13:25:07:242
 * Made by: Tristan
 */
package com.cj4dplex.socket.intergrated.ui.center;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.cj4dplex.socket.intergrated.socket.client.ClientSocket;

public class JSplitPaneCenter extends JSplitPane {

	private static final long serialVersionUID = -968296084631869787L;

	private volatile static JSplitPaneCenter object = null;

	public static JSplitPaneCenter getInstance() {
		if (object == null) {
			synchronized (JSplitPaneCenter.class) {
				if (object == null) {
					object = new JSplitPaneCenter();
				}
			}
		}
		return object;
	}

	private final String[] tableHeader = { "Object", "Type", "Ip Address" };
	public final DefaultTableModel defaultTableModel = new DefaultTableModel(tableHeader, 0);

	public final JTextPane jTextPane = new JTextPane();

	public JSplitPaneCenter() {
		setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		setResizeWeight(0.5F);

		final JTable jTable = new JTable(defaultTableModel) {
			private static final long serialVersionUID = -455120826695075189L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		jTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
			private static final long serialVersionUID = 8146072088783808220L;

			@Override
			public void setHorizontalAlignment(int alignment) {
				super.setHorizontalAlignment(SwingConstants.CENTER);
			}

			@Override
			public Component getTableCellRendererComponent(final JTable table, Object value, boolean isSelected,
					boolean hasFocus, final int row, int column) {
				final Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
						column);
				if (row % 2 == 0) {
					component.setForeground(Color.blue);
				} else {
					component.setForeground(Color.gray);
				}

				final ClientSocket clientSocket = (ClientSocket) table.getValueAt(row, 0);
				clientSocket.setUiSelected(false);

				if (isSelected) {
					clientSocket.setUiSelected(true);
					component.setForeground(Color.green);
				}

				final String udpOrTcp = clientSocket.isUdpOrTcp() ? "TCP" : "UDP";

				table.setValueAt(udpOrTcp, row, 1);
				table.setValueAt(clientSocket.getIpAddressAndPort(), row, 2);

				return component;
			}
		});
		jTable.getColumnModel().getColumn(0).setMinWidth(0);
		jTable.getColumnModel().getColumn(0).setMaxWidth(0);
		jTable.getColumnModel().getColumn(0).setWidth(0);

		setLeftComponent(new JScrollPane(jTable));

		jTextPane.setFocusable(false);
		jTextPane.setOpaque(false);
		jTextPane.setEditable(false);

		setRightComponent(new JScrollPane(jTextPane));
	}
}
